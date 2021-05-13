package cbedoy.pokeflow

import android.util.Log
import poke.common.BuildConfig

const val GLOBAL_TAG = "poke-app"

fun log(tag: String = GLOBAL_TAG, method: String = "", params: String = ""){
    if (BuildConfig.DEBUG){
        Log.d(tag, "------------------------------------------------------------------------")
        Log.d(tag, "Thread - Name: ${Thread.currentThread().name}")
        Log.d(tag, "Thread - Id: ${Thread.currentThread().id}")
        Log.d(tag, "$method: $params")
    }
}

fun logE(tag: String = GLOBAL_TAG, method: String = "", params: String = ""){
    if (BuildConfig.DEBUG){
        Log.e(tag, "------------------------------------------------------------------------")
        Log.e(tag, "Thread - Name: ${Thread.currentThread().name}")
        Log.e(tag, "Thread - Id: ${Thread.currentThread().id}")
        Log.e(tag, "$method: $params")
    }
}

fun log(tag: String, method: String = "", params: List<String>){
    if (BuildConfig.DEBUG){
        Log.d(tag, "------------------------------------------------------------------------")
        Log.d(tag, "Thread - Name: ${Thread.currentThread().name}")
        Log.d(tag, "Thread - Id: ${Thread.currentThread().id}")
        Log.d(tag, method)
        params.forEach {
            Log.d(tag, it)
        }
    }
}
