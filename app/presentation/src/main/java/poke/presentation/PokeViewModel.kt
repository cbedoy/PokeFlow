package poke.presentation

import poke.common.BaseViewModel
import poke.common.Producer
import poke.domain.PokeUseCase
import poke.domain.intent.PokeIntent
import poke.domain.state.PokeState


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