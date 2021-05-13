package poke.presentation.adapter.filter

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import poke.data.model.Filter
import poke.presentation.R
import poke.presentation.databinding.ViewHolderFilterBinding

class FilterTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding by lazy {
        ViewHolderFilterBinding.bind(itemView)
    }

    fun bind(filter: Filter) {
        binding.title.text = filter.title
        if (filter.selected) {
            binding.title.setBackgroundResource(R.drawable.selected_rounded_shape)
            binding.title.setTextColor(Color.BLACK)
        } else {
            binding.title.setBackgroundResource(R.drawable.rounded_shape)
            binding.title.setTextColor(Color.WHITE)
        }
    }
}