package com.compose.movir.data.api


import com.compose.movir.data.models.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface movieAPI {
    @GET("popular?")
    suspend fun getPopularMovies(
        @Query("limit") limit : Int,
        @Query("offset") offset : Int,
        @Query("api_key") apiKey: String,
    ): MovieResponse
}