package br.com.zupflix.data.response

import br.com.zupflix.data.results.MovieResults
import com.google.gson.annotations.SerializedName

data class MovieResponse(

    @SerializedName("results")
    val genreResults: List<MovieResults>
)