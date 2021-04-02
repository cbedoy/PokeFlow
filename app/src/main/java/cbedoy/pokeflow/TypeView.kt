package cbedoy.pokeflow

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import cbedoy.pokeflow.databinding.CustomViewTypeBinding
import java.util.*

class TypeView @JvmOverloads constructor(
    context : Context, attrs : AttributeSet? = null, defStyleAttr : Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding by lazy {
        CustomViewTypeBinding.inflate(LayoutInflater.from(context), this, true)
    }

    init {
        binding.root
    }

    var text: String = ""
        set(value) {
            field = value
            binding.title.text = field.toUpperCase(Locale.ROOT)
        }
}