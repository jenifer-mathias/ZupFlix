package br.com.zupflix.presentation.repository

import android.content.Context
import androidx.lifecycle.LiveData
import br.com.zupflix.data.database.model.FavoriteMovieDAO
import br.com.zupflix.data.database.model.FavoriteMovies
import br.com.zupflix.data.utils.ZupFlixDB

class FavoriteMovieRepository(context: Context) {

    private val favoriteMovieDAO: FavoriteMovieDAO by lazy {
        ZupFlixDB.getDataBase(context).favoriteMovie()
    }

    suspend fun insertMovie(favoriteMovies: FavoriteMovies) {
        favoriteMovieDAO.insertMovie(favoriteMovies)
    }

    fun getFavoriteMovie() : LiveData<FavoriteMovies> = favoriteMovieDAO.getFavoriteMovie()

    fun deleteFavoriteMovie(favoriteMovies: FavoriteMovies) {
        favoriteMovieDAO.deleteFavoriteMovie(favoriteMovies)
    }

}