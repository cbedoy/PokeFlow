package cbedoy.pokeflow.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import cbedoy.pokeflow.SliderTransformer
import cbedoy.pokeflow.ZoomOutPageTransformer
import cbedoy.pokeflow.databinding.FragmentPokePagerBinding
import cbedoy.pokeflow.di.POKE_COUNT
import cbedoy.pokeflow.domain.intent.PokeIntent
import cbedoy.pokeflow.domain.state.PokeState
import cbedoy.pokeflow.helpers.isLandscape
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

    private val gridLayoutManager by lazy {
        GridLayoutManager(
            context, if (isLandscape) 3 else 2, LinearLayoutManager.VERTICAL, false
        )
    }

    override fun onCreateView(
        inflater : LayoutInflater,
        container : ViewGroup?,
        savedInstanceState : Bundle?
    ) = binding.root

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.recyclerView){
            adapter = pokeAdapter
            layoutManager = gridLayoutManager
        }
        binding.creditView.text = "Made with \uD83C\uDF2E❤️ by Carlos Bedoy"
        lifecycleScope.launch {
            viewModel.state.collect {  state ->
                handleState(state)
            }
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.perform(PokeIntent.LoadPokeList)
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.perform(PokeIntent.LoadPokeList)
    }

    private fun handleState(state : PokeState) {
        when(state){
            is PokeState.Ilde -> {
                binding.swipeRefreshLayout.isRefreshing = false
            }
            is PokeState.LoadedTypes -> {

            }
            is PokeState.ReloadPokes -> {
                pokeAdapter.submitList(state.pokes)
            }
            is PokeState.ShowOrHideLoader -> {
                binding.swipeRefreshLayout.isRefreshing = state.isVisible
            }
        }
    }
}