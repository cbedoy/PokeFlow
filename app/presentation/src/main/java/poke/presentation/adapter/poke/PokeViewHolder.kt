package poke.presentation.adapter.poke

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cbedoy.pokeflow.databinding.ViewHolderPokeBinding
import cbedoy.pokeflow.helpers.createHorizontalLinearLayoutManager
import cbedoy.pokeflow.helpers.onClickBounceAnimation
import poke.data.model.Poke
import poke.presentation.adapter.type.TypeAdapter
import coil.load

class PokeViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    private val typeAdapter = TypeAdapter()

    private val binding by lazy {
        ViewHolderPokeBinding.bind(itemView).also {
            with(it.typesRecyclerView){
                layoutManager = context.createHorizontalLinearLayoutManager
                adapter = typeAdapter
            }
        }
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
            typeAdapter.submitList(types)
        }
    }

}