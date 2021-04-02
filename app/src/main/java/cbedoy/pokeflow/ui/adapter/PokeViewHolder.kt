package cbedoy.pokeflow.ui.adapter

import android.view.View
import androidx.core.graphics.toColorInt
import androidx.core.view.isGone
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
            container.setBackgroundColor(poke.color.toColorInt())
            image.load(poke.image){
                crossfade(true)
            }
            image.onClickBounceAnimation()
            name.text = poke.name
            number.text = poke.number.asPokeNumber
            description.text = poke.description

            when {
                poke.type.isEmpty() -> {
                    typeTwo.isVisible = false
                    typeOne.isVisible = false
                }
                poke.type.size == 1 -> {
                    with(typeOne){
                        isVisible = true
                        text = poke.type[0]
                    }
                    typeOne.isVisible = false
                }
                poke.type.size == 2 -> {
                    with(typeOne){
                        isVisible = true
                        text = poke.type[0]
                    }
                    with(typeTwo){
                        isVisible = true
                        text = poke.type[1]
                    }
                }
            }

        }
    }

}