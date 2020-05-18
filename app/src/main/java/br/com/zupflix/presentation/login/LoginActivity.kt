package br.com.zupflix.presentation.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.zupflix.R
import br.com.zupflix.presentation.home.homeactivity.HomeActivity
import br.com.zupflix.presentation.login.loginactivityviewmodel.LoginActivityViewModel
import br.com.zupflix.presentation.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val activityViewModel: LoginActivityViewModel by lazy {
        ViewModelProvider(this).get(LoginActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        createAccount()
        login()

    }

    fun createAccount() {
        txtRegisterNow.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    fun login() {
        buttonLogin.setOnClickListener {
            when(isValid()) {
                0 -> Toast.makeText(this@LoginActivity, "Preencha todos os campos para acessar o aplicativo", Toast.LENGTH_SHORT).show()
                1 -> Toast.makeText(this@LoginActivity, "Preencha o campo senha", Toast.LENGTH_SHORT).show()
                2 -> Toast.makeText(this@LoginActivity, "Preencha corretamente o campo email", Toast.LENGTH_SHORT).show()
                else -> {
                    activityViewModel.getUserDB(edtEmailLogin.text.toString(), edtPasswordLogin.text.toString()).observe(this, Observer { user ->
                        user?.let {
                            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                            finish()
                        } ?: run {
                            Toast.makeText(this@LoginActivity, "Email ou senha inválido", Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }
        }
    }

    fun isValid(): Int {
        return if (TextUtils.isEmpty(edtEmailLogin.text.toString()) && TextUtils.isEmpty(edtPasswordLogin.text.toString())) {
            return 0
        } else if (TextUtils.isEmpty(edtPasswordLogin.text.toString())) {
            return 1
        } else if (!Patterns.EMAIL_ADDRESS.matcher(edtEmailLogin.text.toString()).matches() || TextUtils.isEmpty(edtEmailLogin.text.toString())) {
            return 2
        } else -1
    }
}

