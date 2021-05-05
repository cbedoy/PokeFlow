package cbedoy.pokeflow.domain.state

import cbedoy.pokeflow.model.Filter
import cbedoy.pokeflow.model.Poke

sealed class PokeState {
    object Ilde: PokeState()
    object PerformTopScroll: PokeState()
    data class ShowOrHideEmptyData(
            val isVisible: Boolean,
            val title: String = "",
            val description: String = ""
    ): PokeState()
    data class ShowOrHideLoader(val isVisible: Boolean) : PokeState()
    data class ReloadPokes(val pokes: List<Poke>) : PokeState()
    data class ReloadFilters(val filters: List<Filter>): PokeState()
}