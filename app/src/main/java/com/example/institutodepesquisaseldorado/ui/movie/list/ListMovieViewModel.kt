package com.example.institutodepesquisaseldorado.ui.movie.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.cachedIn
import com.example.institutodepesquisaseldorado.data.model.movie.ListMovieType
import com.example.institutodepesquisaseldorado.data.model.movie.MovieModelData
import com.example.institutodepesquisaseldorado.data.model.movie.MovieModelResponse
import com.example.institutodepesquisaseldorado.repository.IPERepository
import com.example.institutodepesquisaseldorado.ui.state.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ListMovieViewModel @Inject constructor(
    private val repository: IPERepository
) : ViewModel() {

    private val _currentPage                                = MutableStateFlow<Int>(1)
    val currentPage: StateFlow<Int>                         = _currentPage

    val listMovieType                              = MutableStateFlow<ListMovieType>(ListMovieType.NOW_PLAYING)

    private val _list                                       = MutableStateFlow<ResourceState<MovieModelResponse>>(ResourceState.Loading())
    val list: StateFlow<ResourceState<MovieModelResponse>>  = _list

    val pageFlow = Pager(PagingConfig(pageSize = 5)) { pageSourceRepository }.flow.cachedIn(viewModelScope)

    private suspend fun safeFetch() : Flow<ResourceState<MovieModelResponse>> {
        val response = when(listMovieType.value){
            ListMovieType.NOW_PLAYING -> repository.getNowPlaying(page = _currentPage.value)
            ListMovieType.POPULAR ->     repository.getPopular(page = _currentPage.value)
            ListMovieType.TOP_RATED ->   repository.getTopRated(page =  _currentPage.value)
            ListMovieType.UPCOMING ->    repository.getUpcoming(page = _currentPage.value)
        }

        return response.map {
            handleResponse(it)
        }
    }

    private fun handleResponse(response: Response<MovieModelResponse>): ResourceState<MovieModelResponse> {
        if (response.isSuccessful) {
            response.body()?.let { values ->
                return ResourceState.Success(values)
            }
        }

        return ResourceState.Error(response.message())
    }

    private val pageSourceRepository: PagingSource<Int, MovieModelData> get() = object : PagingSource<Int, MovieModelData>() {
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModelData> {
                _currentPage.value = params.key ?: _currentPage.value
                _list.emitAll(safeFetch())
                return if (_list.value.data?.results?.isEmpty() == true) {
                    LoadResult.Invalid()
                } else {
                    val list: List<MovieModelData> = _list.value.data?.results ?: emptyList()
                    LoadResult.Page(
                        data = list,
                        prevKey = null,
                        nextKey = ++_currentPage.value
                    )
                }
            }

            override fun getRefreshKey(state: PagingState<Int, MovieModelData>): Int? =
                state.anchorPosition?.let { anchorPosition ->
                    val anchorPage = state.closestPageToPosition(anchorPosition)
                    anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
                }
        }
}
