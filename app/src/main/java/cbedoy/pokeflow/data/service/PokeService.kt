package cbedoy.pokeflow.data.service

import cbedoy.pokeflow.data.PokeColorResponse
import cbedoy.pokeflow.data.PokeItemResponse
import cbedoy.pokeflow.data.PokeResponse
import cbedoy.pokeflow.data.PokeSpecieResponse
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeService {
    @GET("v2/pokemon")
    suspend fun getPokes(@Query("limit")limit: Int): NetworkResponse<PokeResponse, Unit>

    @GET("v2/pokemon/{pokeId}")
    suspend fun getPokeDetail(@Path("pokeId") productId: String): NetworkResponse<PokeItemResponse, Unit>

    @GET("v2/pokemon-color/{pokeId}")
    suspend fun getPokeColor(@Path("pokeId") productId: String): NetworkResponse<PokeColorResponse, Unit>

    @GET("v2/pokemon-species/{pokeId}")
    suspend fun getSpecie(@Path("pokeId") productId: String): NetworkResponse<PokeSpecieResponse, Unit>
}