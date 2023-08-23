package com.example.institutodepesquisaseldorado.ui.movie.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.institutodepesquisaseldorado.ui.base.BaseFragment
import com.example.institutodepesquisaseldorado.R
import com.example.institutodepesquisaseldorado.data.model.movie.MovieModelData
import com.example.institutodepesquisaseldorado.databinding.FragmentDetailsMovieBinding
import com.example.institutodepesquisaseldorado.util.limitDescription
import com.example.institutodepesquisaseldorado.util.loadImage
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsMovieFragment : BaseFragment<FragmentDetailsMovieBinding, DetailsMovieViewModel>() {
    override val viewModel: DetailsMovieViewModel by viewModels()

    private val args: DetailsMovieFragmentArgs by navArgs()
    private lateinit var movieModel: MovieModelData

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieModel = args.movie

        onLoadedMovie(movieModel)
        binding.tvDescriptionMovieDetails.setOnClickListener {
            onShowDialog(movieModel)
        }
    }

    private fun onShowDialog(movieModel: MovieModelData) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(movieModel.title)
            .setMessage(movieModel.overview)
            .setNegativeButton(getString(R.string.close_dialog)) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    private fun onLoadedMovie(movieModel: MovieModelData) = with(binding) {
        tvNameMovieTitle.text = movieModel.title.limitDescription(35)
        if (movieModel.overview.isEmpty()) {
            tvDescriptionMovieDetails.text = requireContext().getString(R.string.text_description_empty)
        } else {
            tvDescriptionMovieDetails.text = movieModel.overview
        }

        loadImage(imgMovieDetails, movieModel.posterPath)
    }

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentDetailsMovieBinding? =
        FragmentDetailsMovieBinding.inflate(inflater, container, false)
}
