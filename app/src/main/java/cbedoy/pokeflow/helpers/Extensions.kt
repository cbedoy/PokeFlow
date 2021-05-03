package cbedoy.pokeflow.helpers

import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import cbedoy.pokeflow.R

fun ViewGroup.inflate(resId : Int) : View {
    return LayoutInflater.from(context).inflate(resId, this, false)
}

fun TextView.setTextWithVisibility(text: String){
    setText(text)
    isGone = text.isEmpty()
}

val Long.asPokeNumber: String
    get() = String.format("%03d", this);

fun View.onClickBounceAnimation(){
    val bounceAnimation = AnimationUtils.loadAnimation(context, R.anim.bounce)
    setOnClickListener {
        startAnimation(bounceAnimation)
    }
}

fun String.toColor(): Int = Color.parseColor(this)

val Fragment.isLandscape
    get() = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE