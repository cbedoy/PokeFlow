package cbedoy.pokeflow.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import cbedoy.pokeflow.databinding.FragmentPokePagerBinding
import cbedoy.pokeflow.domain.intent.PokeIntent
import cbedoy.pokeflow.domain.state.PokeState
import cbedoy.pokeflow.ui.PokeViewModel
import cbedoy.pokeflow.ui.adapter.PokeAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class PokePagerFragment : Fragment(){
    private val viewModel by viewModel<PokeViewModel>()
    private val pokeAdapter by lazy {
        PokeAdapter()
    }

    private val binding by lazy {
        FragmentPokePagerBinding.inflate(LayoutInflater.from(context))
    }

    override fun onCreateView(
        inflater : LayoutInflater,
        container : ViewGroup?,
        savedInstanceState : Bundle?
    ) = binding.root

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        with(binding.viewPager){
            adapter = pokeAdapter
        }
        lifecycleScope.launch {
            viewModel.state.collect {  state ->
                handleState(state)
            }
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.perform(PokeIntent.LoadPokeList)
    }

    private fun handleState(state : PokeState) {
        when(state){
            is PokeState.Ilde -> {
                binding.progressBar.isVisible = false
            }
            is PokeState.LoadedTypes -> {

            }
            is PokeState.ReloadPokes -> {
                pokeAdapter.submitList(state.pokes)
            }
            is PokeState.ShowOrHideLoader -> {
                binding.progressBar.isVisible = state.isVisible
            }
        }
    }
}