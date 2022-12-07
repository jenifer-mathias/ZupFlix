package br.com.zupflix.presentation.login.loginviewmodel

import android.app.Application
import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import br.com.zupflix.data.database.model.User
import br.com.zupflix.data.database.repository.UserRepository

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = UserRepository(getApplication())

    fun getUserDB(email: String, password: String): LiveData<User> {
        return repository.getUserDB(email, password)
    }

    fun isValid(email: EditText, password: EditText): Int {
        return if (TextUtils.isEmpty(email.text.toString()) && TextUtils.isEmpty(password.text.toString())) {
            return EMAIL_AND_PASSWORD_EMPTY
        } else if (TextUtils.isEmpty(password.text.toString())) {
            return PASSWORD_EMPTY
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString())
                .matches() || TextUtils.isEmpty(email.text.toString())
        ) {
            return EMAIL_NOT_FOUND_OR_EMPTY
        } else LOGIN_VALIDATED
    }

    companion object {
        const val EMAIL_AND_PASSWORD_EMPTY = 0
        const val PASSWORD_EMPTY = 1
        const val EMAIL_NOT_FOUND_OR_EMPTY = 2
        const val LOGIN_VALIDATED = -1
    }

}