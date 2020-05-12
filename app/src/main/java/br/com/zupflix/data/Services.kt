package br.com.zupflix.data


import br.com.zupflix.data.response.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {

    @GET("movie/popular")
    fun getMovies(@Query("api_key") apiKey: String) : Call<MovieResponse>
}