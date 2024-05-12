package com.compose.movir.ui.screens.home

import android.graphics.Movie
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.movir.data.models.MovieResponse
import com.compose.movir.data.models.MovieResult
import com.compose.movir.data.models.genreMap
import com.compose.movir.data.repository.MovieRepository
import com.compose.movir.utils.Constants.API_KEY
import com.compose.movir.utils.Constants.PAGE_SIZE
import com.compose.movir.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(private val repository: MovieRepository) : ViewModel() {

    private var _moviesResult = MutableStateFlow<List<MovieResult>>(emptyList())
    var moviesResult: StateFlow<List<MovieResult>> = _moviesResult.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()



    private var currentPage = 0

    init {
        fetchPopularMovies()
    }

    fun fetchPopularMovies() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.getPopularMovies(limit = PAGE_SIZE, offset = currentPage * PAGE_SIZE, API_KEY)
            _moviesResult.value = result.data!!.results

            val moviesWithGenres = result.data!!.results.map { movie ->
                val genreNames = movie.genre_ids!!.mapNotNull { genreId -> genreMap[genreId] }
                movie.copy(genres = genreNames)
            }
            _moviesResult.value = moviesWithGenres
            when (result) {
                is Resource.Success -> {
                    currentPage++
                    _error.value = null
                    _isLoading.value = false
                }
                is Resource.Error -> {
                    _error.value = result.message!!
                    _isLoading.value = false
                }
            }
        }
    }

}