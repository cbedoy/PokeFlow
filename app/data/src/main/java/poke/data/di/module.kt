package poke.data.di

import android.app.Application
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val repoModule = module {
    single { poke.data.repo.PokeRepository(service = get(), localDataSource = get()) }

    single { poke.data.LocalDataSource(dao = get()) }
}

val serviceModule = module {
    single {
        buildRetrofitInstance("https://pokeapi.co/api/").create(poke.data.service.PokeService::class.java)
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

val databaseModule = module {
    fun provideDatabase(application: Application): poke.data.database.AppDatabase {
        return Room.databaseBuilder(application, poke.data.database.AppDatabase::class.java, "pokes")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun providePokeDao(database: poke.data.database.AppDatabase): poke.data.database.PokeDao {
        return database.pokeDao()
    }

    single { provideDatabase(androidApplication()) }
    single { providePokeDao(get()) }
}

val dataModule = databaseModule + serviceModule + repoModule