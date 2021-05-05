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
import cbedoy.pokeflow.databinding.FragmentPokePagerBinding
import cbedoy.pokeflow.domain.intent.PokeIntent
import cbedoy.pokeflow.domain.state.PokeState
import cbedoy.pokeflow.helpers.createGridLayoutManager
import cbedoy.pokeflow.helpers.createHorizontalLinearLayoutManager
import cbedoy.pokeflow.helpers.isLandscape
import cbedoy.pokeflow.ui.PokeViewModel
import cbedoy.pokeflow.ui.adapter.filter.FilterTypeAdapter
import cbedoy.pokeflow.ui.adapter.poke.PokeAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class PokePagerFragment : Fragment(){
    private val viewModel by viewModel<PokeViewModel>()
    private val pokeAdapter = PokeAdapter()
    private val typesAdapter = FilterTypeAdapter { onSelectedFilter ->
        viewModel.perform(PokeIntent.FilterPokesUsing(onSelectedFilter))
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
            is PokeState.ReloadFilters -> {
                typesAdapter.submitList(null)
                typesAdapter.submitList(state.filters)
            }
            is PokeState.ReloadPokes -> {
                pokeAdapter.submitList(state.pokes)
            }
            is PokeState.ShowOrHideLoader -> {
                binding.swipeRefreshLayout.isRefreshing = state.isVisible
            }
            is PokeState.PerformTopScroll -> {
                binding.recyclerView.post {
                    binding.recyclerView.scrollToPosition(0)
                }
            }
            is PokeState.ShowOrHideEmptyData -> {
                binding.emptyDataView.isVisible = state.isVisible
                binding.emptyDataView.reload(state.title, state.description)
            }
            else -> {

            }
        }
    }
}