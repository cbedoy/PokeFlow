package poke.domain.di

import org.koin.dsl.module
import poke.domain.PokeUseCase

const val POKE_COUNT = 50
const val REFRESHING_OFFSET = 10

val useCaseModule = module {
    single { PokeUseCase(repository = get()) }
}

val domainModule = useCaseModule