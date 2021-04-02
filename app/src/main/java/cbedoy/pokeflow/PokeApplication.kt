package cbedoy.pokeflow

import android.app.Application
import cbedoy.pokeflow.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PokeApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PokeApplication)
            modules(appModule)
        }
    }
}