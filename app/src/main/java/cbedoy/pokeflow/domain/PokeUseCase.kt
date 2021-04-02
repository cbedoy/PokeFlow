package cbedoy.pokeflow.domain

import cbedoy.pokeflow.model.Poke
import cbedoy.pokeflow.data.repo.PokeRepository
import cbedoy.pokeflow.domain.state.PokeState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class PokeUseCase (private val repository : PokeRepository) {

    private var pokes = mutableListOf<Poke>()
    private var colors = mutableListOf<String>()

    val loadPokes
        get() = flow {
            emit(PokeState.ShowOrHideLoader(true))
            repository.getPokes(100).collect {
                emit(PokeState.ShowOrHideLoader(false))

                pokes.add(it)
                colors.add(it.color)

                emit(PokeState.ReloadPokes(pokes))
            }
            emit(PokeState.Ilde)
        }
}