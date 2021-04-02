package cbedoy.pokeflow.helpers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cbedoy.pokeflow.log
import cbedoy.pokeflow.logE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

typealias Producer<T> = suspend (Flow<T>) -> Unit

abstract class BaseViewModel<State, Intent>(
    initialState: State,
) : ViewModel(){
    private val intentChannel = Channel<Intent>(Channel.UNLIMITED)
    private var _state = MutableStateFlow(initialState)
    val state: StateFlow<State>
        get() = _state

    abstract suspend fun onCollect(intent: Intent, producer: Producer<State>)
    abstract val tagLogger: String
    open val currentCoroutineScope: CoroutineScope
        get() = viewModelScope

    fun perform(intent: Intent){
        intentChannel.offer(intent)
    }

    init {
        currentCoroutineScope.launch {
            handleIntents()
        }
    }

    private suspend fun handleIntents(){
        intentChannel.consumeAsFlow().collect { intent ->
            log("ViewModel", "$tagLogger.handleIntent($intent)")
            onCollect(intent){ flow ->
                flow.flowOn(
                    Dispatchers.IO
                ).catch { state ->
                    logE("ViewModel", "$tagLogger.catch(${state.message})")
                }.collect { newState ->
                    log("ViewModel", "$tagLogger.collect($newState)")
                    _state.value = newState
                }
            }
        }
    }
}