package cbedoy.pokeflow.ui.adapter.filter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import cbedoy.pokeflow.R
import cbedoy.pokeflow.helpers.inflate

class FilterTypeAdapter : ListAdapter<String, FilterTypeViewHolder>(
    FilterTypeAdapterDiffUtil
) {
    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : FilterTypeViewHolder {
        return FilterTypeViewHolder(parent.inflate(R.layout.view_holder_filter))
    }

    override fun onBindViewHolder(holder : FilterTypeViewHolder, position : Int) {
        holder.bind(getItem(position))
    }
}

object FilterTypeAdapterDiffUtil : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem : String, newItem : String) : Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem : String, newItem : String) : Boolean {
        return oldItem == newItem
    }

}