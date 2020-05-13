package br.com.zupflix.presentation.repository

import br.com.zupflix.data.ApiService
import br.com.zupflix.data.response.MovieResponse
import retrofit2.Call

class MovieRepository {

    fun getMovies(apiKey: String, language: String) : Call<MovieResponse> {
        return ApiService.service.getMovies(apiKey, language)
    }
}