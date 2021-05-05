# PokeFlow

Funny app which loads pokemons from pokeapi, these are displaying in recyclerview + list adapter. All items are stored in localstorage using room. Just apps to make funny things.

Code based following StateFlow, MVI(State, Intent), Flow, Coroutines, Room + Unit Test

## How it looks

![Poke flow running](https://media.giphy.com/media/rMpMiwv14IJGiIDWfO/giphy.gif)


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
