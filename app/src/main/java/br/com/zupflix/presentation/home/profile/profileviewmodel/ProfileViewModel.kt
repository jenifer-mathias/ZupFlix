package br.com.zupflix.presentation.home.profile.profileviewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import br.com.zupflix.data.database.model.User
import br.com.zupflix.data.database.repository.UserRepository

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = UserRepository(getApplication())

    fun getUserByEmail(email: String) : LiveData<User> = repository.getUserByEmail(email)

}