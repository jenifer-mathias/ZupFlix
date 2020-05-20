package br.com.zupflix.presentation.repository

import br.com.zupflix.data.response.SearchMovieResponse
import br.com.zupflix.data.services.ApiService
import retrofit2.Call

class SearchMovieRepository {

    fun searchMovies(apiKey: String, language: String, query: String, includeAdult: Boolean) : Call<SearchMovieResponse> {
        return ApiService.service.searchMovies(apiKey, language, query, includeAdult)
    }
}