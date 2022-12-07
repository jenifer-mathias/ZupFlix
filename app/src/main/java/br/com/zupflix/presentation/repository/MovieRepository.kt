package br.com.zupflix.presentation.repository

import br.com.zupflix.data.response.MovieResponse
import br.com.zupflix.data.services.ApiService
import retrofit2.Call

class MovieRepository {

    fun getMoviesByGenres(
        apiKey: String,
        language: String,
        includeAdult: Boolean,
        withGenres: Int
    ): Call<MovieResponse> {
        return ApiService.service.getMoviesByGenres(apiKey, language, includeAdult, withGenres)
    }
}