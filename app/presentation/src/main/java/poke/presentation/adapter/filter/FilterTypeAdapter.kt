package poke.presentation.adapter.filter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import cbedoy.pokeflow.helpers.inflate
import poke.data.model.Filter
import poke.presentation.R

class FilterTypeAdapter(
        val onSelectedFilter: (Filter) -> Unit
) : ListAdapter<Filter, FilterTypeViewHolder>(
    FilterTypeAdapterDiffUtil
) {
    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : FilterTypeViewHolder {
        return FilterTypeViewHolder(parent.inflate(R.layout.view_holder_filter))
    }

    override fun onBindViewHolder(holder : FilterTypeViewHolder, position : Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onSelectedFilter(getItem(position))
        }
    }
}

object FilterTypeAdapterDiffUtil : DiffUtil.ItemCallback<Filter>() {
    override fun areItemsTheSame(oldItem : Filter, newItem : Filter) : Boolean {
        return oldItem.selected == newItem.selected
    }

    override fun areContentsTheSame(oldItem : Filter, newItem : Filter) : Boolean {
        return oldItem.title == newItem.title
    }

}