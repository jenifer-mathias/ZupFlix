package br.com.zupflix.presentation.home.favorite.favoriteviewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import br.com.zupflix.data.database.model.FavoriteMovies
import br.com.zupflix.presentation.repository.FavoriteMovieRepository

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = FavoriteMovieRepository(getApplication())

    suspend fun insertMovie(favoriteMovies: FavoriteMovies) = repository.insertMovie(favoriteMovies)

    fun getFavoriteMovie(): LiveData<FavoriteMovies> = repository.getFavoriteMovie()

    fun deleteFavoriteMovie(favoriteMovies: FavoriteMovies) = repository.deleteFavoriteMovie(favoriteMovies)

}