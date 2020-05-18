package br.com.zupflix.presentation.register.registeractivityviewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import br.com.zupflix.data.database.model.User
import br.com.zupflix.data.database.repository.UserRepository

class RegisterActivityViewModel(application: Application): AndroidViewModel(application) {

    private val repository = UserRepository(getApplication())

    suspend fun insertUser(user: User) {
        repository.insertUser(user)
    }
}