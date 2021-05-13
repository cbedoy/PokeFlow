package poke.domain.state

import poke.data.model.Filter
import poke.data.model.Poke

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