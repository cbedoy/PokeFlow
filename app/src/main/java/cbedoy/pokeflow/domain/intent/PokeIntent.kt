package cbedoy.pokeflow.domain.intent

sealed class PokeIntent {
    object LoadPokeList: PokeIntent()
}