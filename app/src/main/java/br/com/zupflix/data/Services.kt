package br.com.zupflix.data


import br.com.zupflix.data.response.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {

    @GET("discover/movie")
    fun getMoviesByGenres(@Query("api_key") apiKey: String,
                          @Query("language") language: String,
                          @Query("include_adult") includeAdult: Boolean,
                          @Query("with_genres") withGenres: Int): Call<MovieResponse>
}

