package cbedoy.pokeflow.ui

import cbedoy.pokeflow.domain.intent.PokeIntent
import cbedoy.pokeflow.domain.state.PokeState
import cbedoy.pokeflow.domain.PokeUseCase
import cbedoy.pokeflow.helpers.BaseViewModel
import cbedoy.pokeflow.helpers.Producer

class PokeViewModel(
    private val useCase : PokeUseCase
) : BaseViewModel<PokeState, PokeIntent>(PokeState.Ilde) {
    override val tagLogger : String
        get() = "poke_view_model"

    override suspend fun onCollect(intent : PokeIntent, producer : Producer<PokeState>) {
        when(intent){
            is PokeIntent.LoadPokeList -> {
                producer(useCase.loadPokes)
            }
            is PokeIntent.FilterPokesUsing -> {
                producer(useCase.filterPokeUsingFilter(intent.filter))
            }
        }
    }
}