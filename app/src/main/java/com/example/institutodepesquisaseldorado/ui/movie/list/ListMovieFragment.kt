package com.example.institutodepesquisaseldorado.ui.movie.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.institutodepesquisaseldorado.R
import com.example.institutodepesquisaseldorado.data.model.movie.MovieModelData
import com.example.institutodepesquisaseldorado.ui.components.adapters.MovieAdapter
import com.example.institutodepesquisaseldorado.ui.base.BaseFragment
import com.example.institutodepesquisaseldorado.ui.state.ResourceState
import com.example.institutodepesquisaseldorado.databinding.FragmentListMovieBinding
import com.example.institutodepesquisaseldorado.ui.base.OnItemClickListener
import com.example.institutodepesquisaseldorado.util.hide
import com.example.institutodepesquisaseldorado.util.show
import com.example.institutodepesquisaseldorado.util.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class ListMovieFragment : BaseFragment<FragmentListMovieBinding, ListMovieViewModel>(),
    OnItemClickListener {

    override val viewModel: ListMovieViewModel by viewModels()
    private val movieAdapter by lazy { MovieAdapter(this) }

    private val args: ListMovieFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.listMovieType.let {
            viewModel.listMovieType.value = it
        }

        setupRecycleView()
        collectObserver()
    }

    private fun collectObserver() = lifecycleScope.launch {
        viewModel.pageFlow.collectLatest {
            movieAdapter.submitData(it)
        }

        viewModel.list.collect { resource ->
            when (resource) {
                is ResourceState.Success -> {
                    resource.data?.let { values ->
                        binding.progressCircular.hide()
                    }
                }

                is ResourceState.Error -> {
                    binding.progressCircular.hide()
                    resource.message?.let { message ->
                        toast(getString(R.string.an_error_occurred))
                        Timber.tag(this.javaClass.name).e("Error -> $message")
                    }
                }

                is ResourceState.Loading -> {
                    binding.progressCircular.show()
                }

                else -> {}
            }
        }
    }

    private fun setupRecycleView() = with(binding) {
        rvMovies.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentListMovieBinding? =
        FragmentListMovieBinding.inflate(inflater, container, false)

    override fun onItemClick(movie: MovieModelData) {
        val action = ListMovieFragmentDirections.actionListMovieFragmentToDetailsMovieFragment(movie)
        findNavController().navigate(action)
    }
}
