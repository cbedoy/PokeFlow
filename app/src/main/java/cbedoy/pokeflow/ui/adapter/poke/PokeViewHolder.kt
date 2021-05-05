package cbedoy.pokeflow.ui.adapter.poke

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import cbedoy.pokeflow.databinding.ViewHolderPokeBinding
import cbedoy.pokeflow.helpers.asPokeNumber
import cbedoy.pokeflow.helpers.onClickBounceAnimation
import cbedoy.pokeflow.model.Poke
import coil.load

class PokeViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    private val binding by lazy {
        ViewHolderPokeBinding.bind(itemView)
    }

    fun bind(poke: Poke){
        with(binding){
            image.load(poke.image){
                crossfade(true)
            }
            image.onClickBounceAnimation()
            name.text = poke.name
            number.text = poke.number.asPokeNumber

            val types = poke.type.split(",")

            when {
                types.isEmpty() -> {
                    typeTwo.isVisible = false
                    typeOne.isVisible = false
                }
                types.size == 1 -> {
                    with(typeOne){
                        isVisible = true
                        text = types[0]
                    }
                    typeOne.isVisible = false
                }
                types.size == 2 -> {
                    with(typeOne){
                        isVisible = true
                        text = types[0]
                    }
                    with(typeTwo){
                        isVisible = true
                        text = types[1]
                    }
                }
            }
        }
    }

}