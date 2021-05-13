package poke.domain.intent

import poke.data.model.Filter


sealed class PokeIntent {
    object LoadPokeList: PokeIntent()
    data class FilterPokesUsing(val filter: Filter): PokeIntent()
}