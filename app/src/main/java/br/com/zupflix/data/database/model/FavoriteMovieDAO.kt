package br.com.zupflix.data.database.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteMovieDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(favoriteMovies: FavoriteMovies)

    @Query("SELECT * FROM favorite_movie")
    fun getFavoriteMovie() : LiveData<FavoriteMovies>

    @Delete
     fun deleteFavoriteMovie(favoriteMovies: FavoriteMovies)
}