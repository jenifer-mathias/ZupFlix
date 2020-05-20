package br.com.zupflix.presentation.register.registeractivityviewmodel

import android.app.Application
import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import br.com.zupflix.data.database.model.User
import br.com.zupflix.data.database.repository.UserRepository
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivityViewModel(application: Application): AndroidViewModel(application) {

    private val repository = UserRepository(getApplication())

    suspend fun insertUser(user: User) {
        repository.insertUser(user)
    }

    fun verifyPhoneNumber(phone: EditText) : String = if (TextUtils.isEmpty(phone.text.toString())) phone.text.toString() else ""

    fun isValid(name: EditText, email: EditText, password: EditText): Int {
        return if (TextUtils.isEmpty(name.text.toString()) && TextUtils.isEmpty(email.text.toString()) && TextUtils.isEmpty(password.text.toString())) {
            return 0
        } else if (TextUtils.isEmpty(name.text.toString())) {
            return 1
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches() || TextUtils.isEmpty(email.text.toString())) {
            return 2
        } else if (TextUtils.isEmpty(password.text.toString())) {
            return 3
        } else -1
    }
}