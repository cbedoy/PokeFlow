package poke.presentation.adapter.type

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import poke.presentation.databinding.ViewHolderFilterBinding
import java.util.*

class TypeViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding by lazy {
        ViewHolderFilterBinding.bind(itemView)
    }

    fun bind(type: String) {
        binding.title.text = type.capitalize(Locale.ROOT)
    }
}