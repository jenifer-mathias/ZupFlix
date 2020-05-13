package br.com.zupflix.presentation.login.loginviewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import br.com.zupflix.data.database.model.User
import br.com.zupflix.data.database.repository.UserRepository

class LoginViewModel(application: Application): AndroidViewModel(application) {

    private val repository = UserRepository(getApplication())

    fun getUserDB(email: String, password: String) : LiveData<User> {
      return repository.getUserDB(email, password)
    }

    suspend fun insertUser(user: User) {
        repository.insertUser(user)
    }
}