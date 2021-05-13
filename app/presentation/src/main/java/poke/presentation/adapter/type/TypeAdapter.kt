package poke.presentation.adapter.type

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import cbedoy.pokeflow.helpers.inflate
import poke.presentation.R

class TypeAdapter: ListAdapter<String, TypeViewHolder>(
        TypeAdapterDiffUtil
) {
    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : TypeViewHolder {
        return TypeViewHolder(parent.inflate(R.layout.view_holder_type))
    }

    override fun onBindViewHolder(holder : TypeViewHolder, position : Int) {
        holder.bind(getItem(position))
    }
}

object TypeAdapterDiffUtil : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem : String, newItem : String) : Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem : String, newItem : String) : Boolean {
        return oldItem == newItem
    }

}