package cbedoy.pokeflow.domain

import cbedoy.pokeflow.data.repo.PokeRepository
import cbedoy.pokeflow.di.POKE_COUNT
import cbedoy.pokeflow.domain.state.PokeState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class PokeUseCase (private val repository : PokeRepository) {

    val loadPokes
        get() = flow {
            emit(PokeState.ShowOrHideLoader(true))
            repository.getPokes(POKE_COUNT).collect { pokes ->
                emit(PokeState.ShowOrHideLoader(false))

                emit(PokeState.ReloadPokes(pokes))
            }
            emit(PokeState.Ilde)
        }
}