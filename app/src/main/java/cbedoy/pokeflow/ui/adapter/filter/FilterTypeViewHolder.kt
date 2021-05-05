package cbedoy.pokeflow.ui.adapter.filter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cbedoy.pokeflow.databinding.ViewHolderFilterBinding

class FilterTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding by lazy {
        ViewHolderFilterBinding.bind(itemView)
    }

    fun bind(name: String){
        binding.title.text = name
    }
}