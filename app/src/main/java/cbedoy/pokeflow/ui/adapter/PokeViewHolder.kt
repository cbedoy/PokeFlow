package cbedoy.pokeflow.ui.adapter

import android.view.View
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import cbedoy.pokeflow.databinding.ViewHolderPokeBinding
import cbedoy.pokeflow.model.Poke
import coil.load

class PokeViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    private val binding by lazy {
        ViewHolderPokeBinding.bind(itemView)
    }

    fun bind(poke: Poke){
        with(binding){
            container.setBackgroundColor(poke.color.toColorInt())
            image.load(poke.image){
                crossfade(true)
            }
            name.text = poke.name
            number.text = poke.type
            description.text = poke.description
        }
    }

}