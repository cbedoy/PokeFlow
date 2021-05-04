package cbedoy.pokeflow

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import cbedoy.pokeflow.databinding.CustomViewFilterBinding
import cbedoy.pokeflow.databinding.CustomViewTypeBinding

class FilterTypeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr){
    private val binding by lazy {
        CustomViewFilterBinding.inflate(LayoutInflater.from(context), this, true)
    }

    init {
        binding.root
    }

    var onSelectFilter : (String, Int) -> Unit = { type, index }

    var types : List<String> = emptyList()
        set(value) {
            field = value

        }
}