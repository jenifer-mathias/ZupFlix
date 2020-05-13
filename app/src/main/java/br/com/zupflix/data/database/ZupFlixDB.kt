package br.com.zupflix.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.zupflix.data.database.model.User

@Database(entities = arrayOf(User::class), version = 1, exportSchema = false)
abstract class ZupFlixDB : RoomDatabase() {

    abstract fun userDao(): UserDAO

    companion object {
        @Volatile
        private var INSTANCE: ZupFlixDB? = null

        fun getDataBase(context: Context) : ZupFlixDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ZupFlixDB::class.java,
                    "zupflix_db")
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }

}