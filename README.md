# PokeFlow

Funny app which loads pokemons from pokeapi, these are displaying in recyclerview + list adapter. All items are stored in localstorage using room. Just apps to make funny things.

Code based following StateFlow, MVI(State, Intent), Flow, Coroutines, Room + Unit Test

## How it looks

![Poke flow running](https://media.giphy.com/media/rMpMiwv14IJGiIDWfO/giphy.gif)

## Configure

Right now you can configure how many pokemons you want to display aka: All pokes.

Look for

```  
// Defined how many pokemons you want to request, every pokemon info is requested one per one, processed and stored in localstorage
const val POKE_COUNT = 50

// Defined after module of pokemons loaded should reload types filter
const val REFRESHING_OFFSET = 10 
```

## Current States

If you're familiar when you use State partern would great try to define what view will perform, hence I defined following states:

```
sealed class PokeState {
    object Ilde: PokeState()
    object PerformTopScroll: PokeState()
    data class ShowOrHideEmptyData(
            val isVisible: Boolean,
            val title: String = "",
            val description: String = ""
    ): PokeState()
    data class ShowOrHideLoader(val isVisible: Boolean) : PokeState()
    data class ReloadPokes(val pokes: List<Poke>) : PokeState()
    data class ReloadFilters(val filters: List<Filter>): PokeState()
}
```

I guest you can deduce what I'm doing

## Current Intents

Also you should get familiar about Intent, right now I'm mapping actions to load pokes and filter pokes, can be more in the future

```
sealed class PokeIntent {
    object LoadPokeList: PokeIntent()
    data class FilterPokesUsing(val filter: Filter): PokeIntent()
}
```

## BaseViewModel

Hell yeah, I've used my custom one implementation of MVI attached to ViewModel look here: https://github.com/cbedoy/PokeFlow/blob/master/app/src/main/java/cbedoy/pokeflow/helpers/BaseViewModel.kt

The great one is I only have to define my State, Intent and InitialState, also never forget you business componet which it going to produce states


```
class ExampleViewModel : BaseMVIViewModel<ExampleState, ExampleIntent>(initialState = ExampleState.Ilde){

    override suspend fun onCollect(intent : ExampleIntent, producer : Producer<PokeState>){
        when(intent){
            ExampleIntent.AnyAmazingDefinition {
                producer(useCase.invokeExample) // producer will invoke collecting in Dispatchers.IO, and refreshing view in Dispatchers.Main :)
            }
        
    }
```

Well you can use alternatives like Orbit-MVI or Uniflow-kt

## Code base structure:

- data: 
    - Contains all DTO responses, Database models, repository, and service
- di
    - Contains all DI injections and configurations, had done with Koin
- domain
    - Contains use case, intent and state.
- util
    - It contains all the helpers, extensions, utils, etc
- presentation
    - Contains fragments, viewmodel, and adapters
    
## Getting Started

1.  Pull down the code locally.
2.  Open Android Studio and select 'Open an existing Android Studio Project'
3.  Navigate to checked out repository.
4.  Inside 'PokeFlow' folder select 'settings.gradle' file
5.  Run the application.

### Libraries I use

|Name|Library |
|----------------|-------------------------------|
|Retrofit|https://github.com/square/retrofit|
|StateFlow|https://developer.android.com/kotlin/flow/stateflow-and-sharedflow|
|Koin|https://insert-koin.io/|
|Room|https://developer.android.com/jetpack/androidx/releases/room?hl=en|
|Coroutines|https://developer.android.com/kotlin/coroutines|

## Contributing
* [Carlos Bedoy](https://www.linkedin.com/in/carlos-cervantes-bedoy-34248187/)
