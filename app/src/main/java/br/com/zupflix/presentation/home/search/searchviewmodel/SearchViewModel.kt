package br.com.zupflix.presentation.home.search.searchviewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.zupflix.data.database.model.FavoriteMovies
import br.com.zupflix.data.response.SearchMovieResponse
import br.com.zupflix.data.results.SearchMovieResults
import br.com.zupflix.presentation.repository.FavoriteMovieRepository
import br.com.zupflix.presentation.repository.SearchMovieRepository
import br.com.zupflix.utils.Constants.ERROR_MESSAGE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel(application: Application) : AndroidViewModel(application) {

    val movieLiveData: MutableLiveData<List<SearchMovieResults>> = MutableLiveData()
    val repository = SearchMovieRepository()
    private val favoriteRepository = FavoriteMovieRepository(getApplication())

    suspend fun insertMovie(favoriteMovies: FavoriteMovies) =
        favoriteRepository.insertMovie(favoriteMovies)

    suspend fun deleteFavoriteMovie(favoriteMovies: FavoriteMovies) =
        favoriteRepository.deleteFavoriteMovie(favoriteMovies)

    fun getFavoriteMovie(userEmail: String): LiveData<List<FavoriteMovies>> =
        favoriteRepository.getFavoriteMovie(userEmail)

    fun searchMovies(query: String, apiKey: String, language: String, includeAdult: Boolean) {

        repository.searchMovies(query, apiKey, language, includeAdult)
            .enqueue(object : Callback<SearchMovieResponse> {

                override fun onResponse(
                    call: Call<SearchMovieResponse>,
                    response: Response<SearchMovieResponse>
                ) {
                    when {
                        response.isSuccessful -> {
                            response.body()?.let { movieResponse ->
                                movieLiveData.value = movieResponse.searchMovie
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<SearchMovieResponse>, t: Throwable) {
                    Log.d(SearchViewModel::class.java.simpleName, ERROR_MESSAGE.plus(t.message))
                }
            })
    }
}