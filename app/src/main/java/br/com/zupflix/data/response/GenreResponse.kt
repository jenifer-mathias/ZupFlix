package br.com.zupflix.data.response

import com.google.gson.annotations.SerializedName

data class GenreResponse(

    @SerializedName("results")
    val genreResults: List<GenreResults>
)