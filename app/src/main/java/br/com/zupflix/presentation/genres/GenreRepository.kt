package br.com.zupflix.presentation.genres

import br.com.zupflix.data.ApiService
import br.com.zupflix.data.response.GenreResponse
import retrofit2.Call

class GenreRepository {

    fun getMoviesByGenres(apiKey: String, language: String, includeAdult: Boolean, withGenres: Int) : Call<GenreResponse> {
        return ApiService.service.getMoviesByGenres(apiKey, language, includeAdult, withGenres)
    }
}