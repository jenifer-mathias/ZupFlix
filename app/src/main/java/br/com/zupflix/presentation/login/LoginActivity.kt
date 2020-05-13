package br.com.zupflix.presentation.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.zupflix.R
import br.com.zupflix.data.database.model.User
import br.com.zupflix.presentation.login.loginviewmodel.LoginViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        GlobalScope.launch {
            viewModel.insertUser(User("jenifer.mathias.santos@hotmail.com", "Jenifer", "123456", "97113-3420"))
        }

        viewModel.getUserDB("jenifer.mathias.santos@hotmail.com", "123456").observe(this, Observer { user ->
            user?.let {
                Log.e(LoginActivity::class.java.simpleName, user.email)
            }

        })
    }
}
