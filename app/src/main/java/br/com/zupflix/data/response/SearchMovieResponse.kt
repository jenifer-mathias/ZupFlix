package br.com.zupflix.data.response

import com.google.gson.annotations.SerializedName

data class SearchMovieResponse(

    @SerializedName("results")
    val searchMovie: List<SearchMovieResults>
)
