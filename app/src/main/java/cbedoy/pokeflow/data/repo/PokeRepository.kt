package cbedoy.pokeflow.data.repo

import cbedoy.pokeflow.data.*
import cbedoy.pokeflow.data.database.PokeDao
import cbedoy.pokeflow.data.service.PokeService
import cbedoy.pokeflow.model.Poke
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.flow.flow

class PokeRepository(
    private val service : PokeService,
    private val localDataSource : LocalDataSource
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

        val localPokes = localDataSource.allPokes
        emit(localPokes)

        when(val response = service.getPokes(count)){
            is NetworkResponse.Success -> {
                response.body.results.forEach { responseItem ->
                    val pokeId = responseItem.id
                    val found = localPokes.firstOrNull { it.number == pokeId?.toLongOrNull()?:0 } != null
                    if (!found) {
                        pokeId?.let {
                            val detail = getDetail(pokeId)
                            val color = getColor(pokeId)
                            val specie = getSpecie(pokeId)
                            val poke = preparePokeWith(detail, color, specie)
                            localDataSource.save(poke)
                            emit(localDataSource.allPokes)
                        }
                    }
                }
            }
        }
    }

    private suspend fun getDetail(pokeId: String) : PokeItemResponse {
        return when(val response = service.getPokeDetail(pokeId)){
            is NetworkResponse.Success -> {
                response.body
            }
            else -> PokeItemResponse()
        }
    }

    private suspend fun getColor(pokeId: String) : PokeColorResponse {
        return when(val response = service.getPokeColor(pokeId)){
            is NetworkResponse.Success -> {
                response.body
            }
            else -> PokeColorResponse()
        }
    }

    private suspend fun getSpecie(pokeId: String) : PokeSpecieResponse {
        return when(val response = service.getSpecie(pokeId)){
            is NetworkResponse.Success -> {
                response.body
            }
            else -> PokeSpecieResponse()
        }
    }

    private fun preparePokeWith(detail : PokeItemResponse, color : PokeColorResponse, specie : PokeSpecieResponse) : Poke {
        return with(detail){
            Poke(
                number = id?:0,
                name = name?.capitalize()?:"",
                image = sprites?.avatar?:"",
                type = typesAsText,
                description = specie.description,
                moves = movesAsText,
                abilities = abilitiesAsText,
                color = colors[color.name] ?:"#a0a0a0"
            )
        }
    }

    private val PokeSpecieResponse.description : String
        get() = flavorTextEntries.firstOrNull { it.flavorText != null }?.flavorText?.removeBreadlines ?: ""

    private val PokeItem.id: String?
        get() = url.split("/").lastOrNull{ it.isNotEmpty() }


    private val PokeItemResponse.typesAsText: String
        get() = types?.joinToString(separator = ",", transform = {
            it.type?.name?:""
        }) ?: ""

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

    val String.removeBreadlines
        get() = replace("\n", "")
}