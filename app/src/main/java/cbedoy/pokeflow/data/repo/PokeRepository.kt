package cbedoy.pokeflow.data.repo

import cbedoy.pokeflow.data.*
import cbedoy.pokeflow.data.service.PokeService
import cbedoy.pokeflow.model.Poke
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.flow.flow

class PokeRepository(
    private val service : PokeService
) {
    private val colors = mapOf(
        "black" to "#292929",
        "blue" to "#2062ac",
        "grey" to "#292929",
        "yellow" to "#f6bd20",
        "red" to "#c52018",
        "white" to "#FAFAFA",
        "brown" to "#8b6241",
        "green" to "#6ad531",
        "orange" to "#ff7b41",
    )


    suspend fun getPokes(count: Int) = flow {
        when(val response = service.getPokes(count)){
            is NetworkResponse.Success -> {
                response.body.results.forEach { responseItem ->
                    val pokeId = responseItem.id
                    pokeId?.let {
                        val detail = service.getPokeDetail(pokeId)
                        val color = service.getPokeColor(pokeId)
                        val specie = service.getSpecie(pokeId)
                        val poke = preparePokeWith(detail, color, specie)
                        emit(poke)
                    }
                }
            }
        }
    }

    private fun preparePokeWith(detail : PokeItemResponse, color : PokeColorResponse, specie : PokeSpecieResponse) : Poke {
        return with(detail){
            Poke(
                name = name?.capitalize()?:"",
                image = sprites?.avatar?:"",
                type = types?.joinToString(separator = "/", transform = {
                    it.type?.name?:""
                })?:"",
                description = specie.description,
                moves = movesAsText,
                abilities = abilitiesAsText,
                color = colors[color.name] ?:"#a0a0a0"
            )
        }
    }

    val PokeSpecieResponse.description : String
        get() = flavorTextEntries.firstOrNull { it.flavorText != null }?.flavorText?: ""

    val PokeItem.id: String?
        get() = url.split("/").lastOrNull{ it.isNotEmpty() }

    val PokeItemResponse.movesAsText: String
        get() = moves?.joinToString(
            separator = "\n",
            transform = {
                "- ${it.move?.name?:""}"
            }) ?: ""

    val PokeItemResponse.abilitiesAsText: String
        get() = abilities?.joinToString(
            separator = "\n",
            transform = {
                "- ${it.ability?.name?:""}"
            }) ?: ""

}