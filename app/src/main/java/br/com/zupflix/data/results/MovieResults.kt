package br.com.zupflix.data.results

import com.google.gson.annotations.SerializedName

class MovieResults(

    @SerializedName("id")
    val id: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String
)