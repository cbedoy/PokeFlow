package cbedoy.pokeflow.domain.state

import cbedoy.pokeflow.model.Poke

sealed class PokeState {
    object Ilde: PokeState()
    data class ShowOrHideLoader(val isVisible: Boolean) : PokeState()
    data class ReloadPokes(val pokes: List<Poke>) : PokeState()
    data class LoadedTypes(val types: List<String>): PokeState()
}