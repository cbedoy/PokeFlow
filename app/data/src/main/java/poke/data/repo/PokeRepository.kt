package poke.data.repo

import poke.data.service.PokeService
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.flow.flow
import poke.data.*
import poke.data.model.Filter
import poke.data.model.Poke
import java.util.*

class PokeRepository(
    private val service : PokeService,
    private val localDataSource : LocalDataSource
) {
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
                            //val color = getColor(pokeId)
                            //val specie = getSpecie(pokeId)
                            val poke = preparePokeWith(
                                detail = detail,
                                color = null,
                                specie = null
                            )
                            localDataSource.save(poke)
                            emit(localDataSource.allPokes)
                        }
                    }
                }
            }
        }
    }

    val loadTypes = flow {
        val typesSet = mutableSetOf<Filter>().apply {
            add(Filter(selected = true, "All Pokes"))
        }
        localDataSource.allTypes.map { types ->
            typesSet.addAll(types.split(",").map { splitResult ->
                Filter(title = splitResult.capitalize(Locale.ROOT))
            })
        }
        emit(typesSet.toList())
    }

    fun filterUsing(filter: Filter) = flow {
        if (filter.title == "All Pokes"){
            val localPokes = localDataSource.allPokes
            emit(localPokes)
        } else {
            val pokes = localDataSource.pokesFilterBy(filter.title)
            emit(pokes)
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

    private fun preparePokeWith(detail : PokeItemResponse, color : PokeColorResponse?, specie : PokeSpecieResponse?) : Poke {
        return with(detail){
            Poke(
                number = id?:0,
                name = name?.capitalize()?:"",
                image = sprites?.avatar?:"",
                type = typesAsText,
                description = specie?.description?:"",
                moves = movesAsText,
                abilities = abilitiesAsText,
                color = ""
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