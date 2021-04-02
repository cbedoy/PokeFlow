package cbedoy.pokeflow.di

import cbedoy.pokeflow.BuildConfig
import cbedoy.pokeflow.data.repo.PokeRepository
import cbedoy.pokeflow.data.service.PokeService
import cbedoy.pokeflow.domain.PokeUseCase
import cbedoy.pokeflow.ui.PokeViewModel
import cbedoy.pokeflow.ui.view.PokePagerFragment
import com.google.gson.GsonBuilder
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val POKE_COUNT = 50

val viewModelModule = module {
    viewModel {
        PokeViewModel(useCase = get())
    }
}

val fragmentModule = module {
    factory {
        PokePagerFragment()
    }
}

val repoModule = module {
    single { PokeRepository(service = get()) }
}

val useCaseModule = module {
    single { PokeUseCase(repository = get()) }
}

val service = module {
    single {
        buildRetrofitInstance("https://pokeapi.co/api/").create(PokeService::class.java)
    }
}

fun buildRetrofitInstance(baseUrl: String): Retrofit {
    val httpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

    addLoggingInterceptor(httpBuilder)

    val httpClient = httpBuilder
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .client(httpClient)
        .build()
}


private fun addLoggingInterceptor(httpBuilder: OkHttpClient.Builder) {
    val logging = HttpLoggingInterceptor()
    logging.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    httpBuilder.addInterceptor(logging)
}

val appModule = viewModelModule + repoModule + useCaseModule + service + fragmentModule