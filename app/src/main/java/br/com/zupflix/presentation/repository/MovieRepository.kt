package br.com.zupflix.presentation.repository

import br.com.zupflix.data.ApiService
import br.com.zupflix.data.response.MovieResponse
import retrofit2.Call

class MovieRepository {

    fun getMoviesByGenres(apiKey: String, language: String, includeAdult: Boolean, withGenres: Int) : Call<MovieResponse> {
        return ApiService.service.getMoviesByGenres(apiKey, language, includeAdult, withGenres)
    }
}