package br.com.zupflix.data.database.repository

import android.content.Context
import androidx.lifecycle.LiveData
import br.com.zupflix.data.database.model.UserDAO
import br.com.zupflix.data.database.ZupFlixDB
import br.com.zupflix.data.database.model.User

class UserRepository(context: Context) {

    private val userDAO: UserDAO by lazy {
        ZupFlixDB.getDataBase(context).userDao()
    }

    suspend fun insertUser(user: User) {
        userDAO.insertUser(user)
    }

    fun getUserDB(email: String, password: String): LiveData<User> = userDAO.getUser(email, password)

    fun getUserByEmail(email: String) : LiveData<User> = userDAO.getUserByEmail(email)

}