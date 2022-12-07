package br.com.zupflix.presentation.register.registerviewmodel

import android.app.Application
import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import androidx.lifecycle.AndroidViewModel
import br.com.zupflix.data.database.model.User
import br.com.zupflix.data.database.repository.UserRepository

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = UserRepository(getApplication())

    suspend fun insertUser(user: User) {
        repository.insertUser(user)
    }

    fun verifyPhoneNumber(phone: EditText): String =
        if (TextUtils.isEmpty(phone.text.toString())) phone.text.toString() else ""

    fun isValid(name: EditText, email: EditText, password: EditText): Int {
        return if (TextUtils.isEmpty(name.text.toString()) && TextUtils.isEmpty(email.text.toString()) && TextUtils.isEmpty(
                password.text.toString()
            )
        ) {
            return NAME_AND_EMAIL_EMPTY
        } else if (TextUtils.isEmpty(name.text.toString())) {
            return NAME_EMPTY
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString())
                .matches() || TextUtils.isEmpty(email.text.toString())
        ) {
            return EMAIL_NOT_FOUND_OR_EMPTY
        } else if (TextUtils.isEmpty(password.text.toString())) {
            return PASSWORD_EMPTY
        } else LOGIN_VALIDATED
    }

    companion object {
        const val NAME_AND_EMAIL_EMPTY = 0
        const val NAME_EMPTY = 1
        const val EMAIL_NOT_FOUND_OR_EMPTY = 2
        const val PASSWORD_EMPTY = 3
        const val LOGIN_VALIDATED = -1
    }
}