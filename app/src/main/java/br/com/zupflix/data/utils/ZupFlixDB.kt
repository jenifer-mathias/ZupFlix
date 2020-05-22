package br.com.zupflix.data.utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import br.com.zupflix.data.database.converters.Converters
import br.com.zupflix.data.database.model.FavoriteMovieDAO
import br.com.zupflix.data.database.model.FavoriteMovies
import br.com.zupflix.data.database.model.User
import br.com.zupflix.data.database.model.UserDAO

@Database(entities = arrayOf(User::class, FavoriteMovies::class), version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ZupFlixDB : RoomDatabase() {

    abstract fun userDao(): UserDAO
    abstract fun favoriteMovie(): FavoriteMovieDAO

    companion object {
        @Volatile
        private var INSTANCE: ZupFlixDB? = null

        val MIGRATION_1_2 : Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
               // database.execSQL("create table favorite_movie"()) como não alteramos a tabela, não precisamos adicionar nada
            }

        }

        fun getDataBase(context: Context) : ZupFlixDB {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ZupFlixDB::class.java,
                    "zupflix_db")
                    .addMigrations(MIGRATION_1_2)
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }

}