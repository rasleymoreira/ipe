package com.example.institutodepesquisaseldorado.ui.movie.details

import androidx.lifecycle.ViewModel
import com.example.institutodepesquisaseldorado.repository.IPERepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsMovieViewModel @Inject constructor(
    private val repository: IPERepository
) : ViewModel() {
    //TODO
}
