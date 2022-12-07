package br.com.zupflix.presentation.repository

import android.content.Context
import androidx.lifecycle.LiveData
import br.com.zupflix.data.database.ZupFlixDB
import br.com.zupflix.data.database.model.FavoriteMovieDAO
import br.com.zupflix.data.database.model.FavoriteMovies

class FavoriteMovieRepository(context: Context) {

    private val favoriteMovieDAO: FavoriteMovieDAO by lazy {
        ZupFlixDB.getDataBase(context).favoriteMovie()
    }

    suspend fun insertMovie(favoriteMovies: FavoriteMovies) {
        favoriteMovieDAO.insertMovie(favoriteMovies)
    }

    fun getFavoriteMovie(userEmail: String): LiveData<List<FavoriteMovies>> =
        favoriteMovieDAO.getFavoriteMovie(userEmail)

    suspend fun deleteFavoriteMovie(favoriteMovies: FavoriteMovies) {
        favoriteMovieDAO.deleteFavoriteMovie(favoriteMovies)
    }

}