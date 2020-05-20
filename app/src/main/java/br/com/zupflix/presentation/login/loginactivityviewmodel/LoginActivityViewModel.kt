package br.com.zupflix.presentation.login.loginactivityviewmodel

import android.app.Application
import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import br.com.zupflix.data.database.model.User
import br.com.zupflix.data.database.repository.UserRepository
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivityViewModel(application: Application): AndroidViewModel(application) {

    private val repository = UserRepository(getApplication())

    fun getUserDB(email: String, password: String) : LiveData<User> {
      return repository.getUserDB(email, password)
    }

    fun isValid(email: EditText, password: EditText): Int {
        return if (TextUtils.isEmpty(email.text.toString()) && TextUtils.isEmpty(password.text.toString())) {
            return 0
        } else if (TextUtils.isEmpty(password.text.toString())) {
            return 1
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches() || TextUtils.isEmpty(email.text.toString())) {
            return 2
        } else -1
    }

}