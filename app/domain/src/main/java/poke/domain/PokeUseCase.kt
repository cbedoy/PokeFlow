package poke.domain

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import poke.domain.state.PokeState
import poke.data.model.Filter
import poke.data.model.Poke
import poke.data.repo.PokeRepository
import poke.domain.di.POKE_COUNT
import poke.domain.di.REFRESHING_OFFSET

class PokeUseCase (private val repository : PokeRepository) {

    private val currentFilters = mutableListOf<Filter>()
    private val currentPokes = mutableListOf<Poke>()

    val loadPokes
        get() = flow {
            emit(PokeState.ShowOrHideLoader(true))
            emit(PokeState.ShowOrHideEmptyData(isVisible = false))

            repository.getPokes(POKE_COUNT).collect { pokes ->
                emit(PokeState.ReloadPokes(pokes))

                currentPokes.addAll(pokes)

                if (currentPokes.size % REFRESHING_OFFSET == 0){
                    repository.loadTypes.collect { filters ->
                        currentFilters.clear()
                        currentFilters.addAll(filters)

                        emit(PokeState.ReloadFilters(currentFilters))
                    }
                }
            }
            emit(PokeState.ShowOrHideLoader(false))
            emit(PokeState.Ilde)
        }

    fun filterPokeUsingFilter(filter: Filter) = flow {
        emit(PokeState.ShowOrHideLoader(true))
        repository.filterUsing(filter).collect { pokes ->
            emit(PokeState.ReloadPokes(pokes))
            emit(
                PokeState.ShowOrHideEmptyData(
                    isVisible = pokes.isEmpty(),
                    title = "So sad :[",
                    description = "Looks like ASH caught all ${filter.title}'s pokes"
                ))
        }

        val newFilters = currentFilters.map { currentFilter ->
            currentFilter.copy(selected = filter.title == currentFilter.title)
        }.toMutableList()

        currentFilters.clear()
        currentFilters.addAll(newFilters)

        emit(PokeState.ReloadFilters(currentFilters))
        emit(PokeState.PerformTopScroll)
        emit(PokeState.ShowOrHideLoader(false))
        emit(PokeState.Ilde)
    }
}