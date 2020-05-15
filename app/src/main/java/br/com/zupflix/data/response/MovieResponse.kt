package br.com.zupflix.data.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(

    @SerializedName("results")
    val genreResults: List<MovieResults>
)