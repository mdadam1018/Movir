package com.compose.movir.data.repository
import com.compose.movir.data.api.movieAPI
import com.compose.movir.data.models.MovieResponse
import com.compose.movir.utils.Resource
import javax.inject.Inject

class MovieRepository@Inject constructor(private val apiService: movieAPI) {
    suspend fun getPopularMovies(limit :Int,offset:Int,api_key:String): Resource<MovieResponse> {
        var response = try {
            apiService.getPopularMovies(limit,offset,api_key)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred")
        }
        return Resource.Success(response)
    }
}