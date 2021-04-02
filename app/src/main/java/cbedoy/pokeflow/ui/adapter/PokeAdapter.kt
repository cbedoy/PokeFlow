package cbedoy.pokeflow.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import cbedoy.pokeflow.R
import cbedoy.pokeflow.helpers.inflate
import cbedoy.pokeflow.model.Poke

class PokeAdapter : ListAdapter<Poke, PokeViewHolder>(
    PokeAdapterDiffUtil
) {
    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : PokeViewHolder {
        return PokeViewHolder(parent.inflate(R.layout.view_holder_poke))
    }

    override fun onBindViewHolder(holder : PokeViewHolder, position : Int) {
        holder.bind(getItem(position))
    }
}

object PokeAdapterDiffUtil : DiffUtil.ItemCallback<Poke>() {
    override fun areItemsTheSame(oldItem : Poke, newItem : Poke) =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem : Poke, newItem : Poke) =
        oldItem == newItem
}