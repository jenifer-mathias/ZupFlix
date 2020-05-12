package br.com.zupflix.data.response

import com.google.gson.annotations.SerializedName

class MovieResponse (

    @SerializedName("results")
    val movieResults: List<MovieResults>
)