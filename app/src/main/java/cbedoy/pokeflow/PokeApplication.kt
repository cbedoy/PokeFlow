package cbedoy.pokeflow

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import poke.data.di.dataModule
import poke.domain.di.domainModule
import poke.presentation.di.presentationModule

class PokeApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PokeApplication)
            modules(modules = presentationModule + domainModule + dataModule)
        }
    }
}