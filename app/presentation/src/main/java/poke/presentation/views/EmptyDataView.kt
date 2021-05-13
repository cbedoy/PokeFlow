package poke.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import poke.presentation.databinding.CustomViewEmptyDataBinding

class EmptyDataView @JvmOverloads constructor(
    context : Context, attrs : AttributeSet? = null, defStyleAttr : Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding by lazy {
        CustomViewEmptyDataBinding.inflate(LayoutInflater.from(context), this, true)
    }

    init {
        binding.root
    }

    fun reload(title: String, description: String){
        binding.title.text = title
        binding.description.text = description
    }
}