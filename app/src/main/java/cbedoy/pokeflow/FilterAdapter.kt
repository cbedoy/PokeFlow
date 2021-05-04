package cbedoy.pokeflow

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cbedoy.pokeflow.databinding.ViewHolderFilterBinding
import cbedoy.pokeflow.databinding.ViewHolderPokeBinding

class FilterAdapter {
}


class FilterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding by lazy {
        ViewHolderFilterBinding.bind(itemView)
    }

    fun bind(name: String){
        binding.title.text = name
    }
}