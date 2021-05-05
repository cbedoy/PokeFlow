package cbedoy.pokeflow.domain.intent

import cbedoy.pokeflow.model.Filter

sealed class PokeIntent {
    object LoadPokeList: PokeIntent()
    data class FilterPokesUsing(val filter: Filter): PokeIntent()
}