package poke.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import poke.presentation.PokeViewModel
import poke.presentation.ui.PokePagerFragment

const val POKE_COUNT = 50
const val REFRESHING_OFFSET = 10

private val viewModelModule = module {
    viewModel {
        PokeViewModel(useCase = get())
    }
}

private val fragmentModule = module {
    factory {
        PokePagerFragment()
    }
}

val presentationModule = viewModelModule + fragmentModule