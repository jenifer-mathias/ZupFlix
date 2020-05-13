package br.com.zupflix.presentation.genres.adventure.adventurefragmentviewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zupflix.data.response.GenreResponse
import br.com.zupflix.data.response.GenreResults
import br.com.zupflix.presentation.genres.GenreRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdventureFragmentViewModel : ViewModel() {

    val genreMovieLiveData: MutableLiveData<List<GenreResults>> = MutableLiveData()
    val repository = GenreRepository()

    fun getMoviesByGenres(
        apiKey: String,
        language: String,
        includeAdult: Boolean,
        withGenres: Int
    ) {
        repository.getMoviesByGenres(apiKey, language, includeAdult, withGenres)
            .enqueue(object : Callback<GenreResponse> {

                override fun onResponse(
                    call: Call<GenreResponse>,
                    response: Response<GenreResponse>
                ) {
                    when {
                        response.isSuccessful -> {
                            response.body()?.let { genreResponse ->
                                genreMovieLiveData.value = genreResponse.genreResults
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<GenreResponse>, t: Throwable) {
                    Log.d(AdventureFragmentViewModel::class.java.simpleName, "ERROR: ${t.message}")
                }
            })
    }
}