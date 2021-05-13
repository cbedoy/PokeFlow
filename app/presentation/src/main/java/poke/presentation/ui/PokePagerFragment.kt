package poke.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import cbedoy.pokeflow.helpers.createGridLayoutManager
import cbedoy.pokeflow.helpers.createHorizontalLinearLayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import poke.presentation.PokeViewModel
import poke.presentation.adapter.filter.FilterTypeAdapter
import poke.presentation.adapter.poke.PokeAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import poke.presentation.databinding.FragmentPokePagerBinding


class PokePagerFragment : Fragment(){
    private val viewModel by viewModel<PokeViewModel>()
    private val pokeAdapter = PokeAdapter()
    private val typesAdapter = FilterTypeAdapter { onSelectedFilter ->
        viewModel.perform(poke.domain.intent.PokeIntent.FilterPokesUsing(onSelectedFilter))
    }

    private val binding by lazy {
        FragmentPokePagerBinding.inflate(LayoutInflater.from(context))
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
            layoutManager = context.createGridLayoutManager
        }
        with(binding.typesRecyclerView){
            adapter = typesAdapter
            layoutManager = context.createHorizontalLinearLayoutManager
        }
        binding.creditView.text = "Made with \uD83C\uDF2E❤️ by Carlos Bedoy"
        lifecycleScope.launch {
            viewModel.state.collect {
                handleState(it)
            }
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.perform(poke.domain.intent.PokeIntent.LoadPokeList)
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.perform(poke.domain.intent.PokeIntent.LoadPokeList)
    }

    private fun handleState(state : poke.domain.state.PokeState) {
        when(state){
            is poke.domain.state.PokeState.Ilde -> {
                binding.swipeRefreshLayout.isRefreshing = false
            }
            is poke.domain.state.PokeState.ReloadFilters -> {
                typesAdapter.submitList(null)
                typesAdapter.submitList(state.filters)
            }
            is poke.domain.state.PokeState.ReloadPokes -> {
                pokeAdapter.submitList(state.pokes)
            }
            is poke.domain.state.PokeState.ShowOrHideLoader -> {
                binding.swipeRefreshLayout.isRefreshing = state.isVisible
            }
            is poke.domain.state.PokeState.PerformTopScroll -> {
                binding.recyclerView.post {
                    binding.recyclerView.scrollToPosition(0)
                }
            }
            is poke.domain.state.PokeState.ShowOrHideEmptyData -> {
                binding.emptyDataView.isVisible = state.isVisible
                binding.emptyDataView.reload(state.title, state.description)
            }
            else -> {

            }
        }
    }
}