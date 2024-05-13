package com.compose.movir.di

import com.compose.movir.data.api.movieAPI
import com.compose.movir.data.repository.MovieRepository
import com.compose.movir.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun MovieListRepository(
        api: movieAPI
    ) = MovieRepository(api)


    @Provides
    fun provideApiService(): movieAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(movieAPI::class.java)
    }

}