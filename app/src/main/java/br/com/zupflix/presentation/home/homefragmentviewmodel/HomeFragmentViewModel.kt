package br.com.zupflix.presentation.home.homefragmentviewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zupflix.data.response.MovieResponse
import br.com.zupflix.data.response.MovieResults
import br.com.zupflix.presentation.repository.MovieRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragmentViewModel : ViewModel() {

    val movieLiveData: MutableLiveData<List<MovieResults>> = MutableLiveData()
    val repository = MovieRepository()

    fun getMovie(apiKey: String) {
        repository.getMovies(apiKey).enqueue(object : Callback<MovieResponse> {

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                when {
                    response.isSuccessful -> {

                        response.body()?.let { movieResponse ->
                            movieLiveData.value = movieResponse.movieResults
                        }
                    }
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d(HomeFragmentViewModel::class.java.simpleName, "ERROR: ${t.message}")
            }
        })
    }
}