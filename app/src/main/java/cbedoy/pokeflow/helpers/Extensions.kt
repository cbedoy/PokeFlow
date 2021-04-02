package cbedoy.pokeflow.helpers

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import cbedoy.pokeflow.R

fun ViewGroup.inflate(resId : Int) : View {
    return LayoutInflater.from(context).inflate(resId, this, false)
}


fun String.toColor(): Int = Color.parseColor(this)