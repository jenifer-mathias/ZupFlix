package br.com.zupflix.presentation.home.genres.action.actionfragmentviewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zupflix.data.response.MovieResponse
import br.com.zupflix.data.response.MovieResults
import br.com.zupflix.presentation.repository.MovieRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActionFragmentViewModel : ViewModel() {

    val movieMovieLiveData: MutableLiveData<List<MovieResults>> = MutableLiveData()
    val repository = MovieRepository()

    fun getMoviesByGenres(
        apiKey: String,
        language: String,
        includeAdult: Boolean,
        withGenres: Int
    ) {
        repository.getMoviesByGenres(apiKey, language, includeAdult, withGenres)
            .enqueue(object : Callback<MovieResponse> {

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    when {
                        response.isSuccessful -> {
                            response.body()?.let { genreResponse ->
                                movieMovieLiveData.value = genreResponse.genreResults
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.d(ActionFragmentViewModel::class.java.simpleName, "ERROR: ${t.message}")
                }
            })
    }
}