package br.com.zupflix.presentation.home.search.searchviewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zupflix.data.response.SearchMovieResponse
import br.com.zupflix.data.response.SearchMovieResults
import br.com.zupflix.presentation.repository.SearchMovieRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {

    val movieMovieLiveData: MutableLiveData<List<SearchMovieResults>> = MutableLiveData()
    val repository = SearchMovieRepository()

    fun searchMovies(query: String, apiKey: String, language: String, includeAdult: Boolean) {

        repository.searchMovies(query, apiKey, language, includeAdult)
            .enqueue(object : Callback<SearchMovieResponse> {

                override fun onResponse(call: Call<SearchMovieResponse>,
                    response: Response<SearchMovieResponse>) {
                    when {
                        response.isSuccessful -> {
                            response.body()?.let { movieResponse ->
                                movieMovieLiveData.value = movieResponse.searchMovie
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<SearchMovieResponse>, t: Throwable) {
                    Log.d(SearchViewModel::class.java.simpleName, "ERROR: ${t.message}")
                }
            })
    }
}