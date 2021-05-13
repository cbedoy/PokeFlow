package poke.data

import com.google.gson.annotations.SerializedName

data class PokeResponse(
    val results: List<PokeItem> = emptyList()
)

data class PokeItem(
    val name: String = "",
    val url: String = ""
)

data class PokeSpecieResponse(
    val id: String? = null,
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntriesResponse> = emptyList()
)

data class FlavorTextEntriesResponse(
    @SerializedName("flavor_text")
    val flavorText: String? = null
)

data class PokeColorResponse(
    val id: String? = null,
    val name: String? = null
)

data class PokeItemResponse(
    val id: Long? = null,
    val name: String? = "",
    val url: String? = "",
    val weight: Int? = 0,
    val sprites: PokeSpriteResponse? = PokeSpriteResponse(),
    val types: List<PokeTypesResponse>? = emptyList(),
    val moves: List<PokeMovesResponse>? = emptyList(),
    val abilities: List<PokeAbilitiesResponse>? = emptyList(),
)

data class PokeAbilitiesResponse(
    val ability: PokeAbilityResponse? = PokeAbilityResponse()
)

data class PokeAbilityResponse(
    val name: String? = ""
)

data class PokeSpriteResponse(
    @SerializedName("front_default")
    val avatar: String? = "",
    val other: PokeOtherResponse? = null
)

data class PokeOtherResponse(
    @SerializedName("dream_world")
    val dreamWorld: DreamWorldResponse? = null
)

data class DreamWorldResponse(
    @SerializedName("front_default")
    val avatar: String? = ""
)

data class PokeMovesResponse(
    val move: PokeMoveResponse? = PokeMoveResponse()
)

data class PokeMoveResponse(
    val name: String? = ""
)

data class PokeTypesResponse(
    val type: PokeTypeResponse? = PokeTypeResponse()
)

data class PokeTypeResponse(
    val name: String? = ""
)