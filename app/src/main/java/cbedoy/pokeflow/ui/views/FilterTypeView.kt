package cbedoy.pokeflow.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import cbedoy.pokeflow.databinding.CustomViewFilterBinding
import cbedoy.pokeflow.helpers.createHorizontalLinearLayoutManager
import cbedoy.pokeflow.ui.adapter.filter.FilterTypeAdapter

typealias OnSelectedFilter = (String, Int) -> Unit

class FilterTypeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr){

    private val filterTypeAdapter = FilterTypeAdapter()

    private val binding by lazy {
        CustomViewFilterBinding.inflate(LayoutInflater.from(context), this, true)
    }

    init {
        binding.root
        with(binding.recyclerView){
            adapter = filterTypeAdapter
            layoutManager = context.createHorizontalLinearLayoutManager
        }
        filterTypeAdapter.submitList(listOf(
            "All pokes", "Fire", "Air"
        ))
    }

    var onSelectFilter : OnSelectedFilter = { name, index ->

    }

    var types : List<String> = emptyList()
        set(value) {
            field = value
            filterTypeAdapter.submitList(types)
        }
}