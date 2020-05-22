package br.com.zupflix.data.response

import br.com.zupflix.data.results.SearchMovieResults
import com.google.gson.annotations.SerializedName

data class SearchMovieResponse(

    @SerializedName("results")
    val searchMovie: List<SearchMovieResults>
)
