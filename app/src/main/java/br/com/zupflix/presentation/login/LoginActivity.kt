package br.com.zupflix.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.zupflix.R
import br.com.zupflix.data.utils.SharedPreference
import br.com.zupflix.databinding.ActivityLoginBinding
import br.com.zupflix.presentation.base.BaseActivity
import br.com.zupflix.presentation.home.homeactivity.HomeActivity
import br.com.zupflix.presentation.login.loginviewmodel.LoginViewModel
import br.com.zupflix.presentation.register.RegisterActivity
import br.com.zupflix.utils.Constants.USER
import br.com.zupflix.utils.viewBinding

class LoginActivity : BaseActivity() {

    private val binding by viewBinding(ActivityLoginBinding::inflate)

    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupToolbar(binding.toolbarMovie.toolbar, R.string.txt_toolbar_name, false)

        val sharedPreference = SharedPreference(this)
        sharedPreference.getData(USER)?.let { email ->
            binding.edtEmailLogin.setText(email)
        }
        createAccount()

        login(sharedPreference)
    }

    fun login(sharedPreference: SharedPreference) {
        binding.buttonLogin.setOnClickListener {
            when (viewModel.isValid(binding.edtEmailLogin, binding.edtPasswordLogin)) {
                0 -> Toast.makeText(
                    this@LoginActivity,
                    "Preencha todos os campos para acessar o aplicativo",
                    Toast.LENGTH_SHORT
                ).show()
                1 -> Toast.makeText(
                    this@LoginActivity,
                    "Preencha o campo senha",
                    Toast.LENGTH_SHORT
                ).show()
                2 -> Toast.makeText(
                    this@LoginActivity,
                    "Preencha corretamente o campo email",
                    Toast.LENGTH_SHORT
                ).show()
                else -> {
                    viewModel.getUserDB(
                        binding.edtEmailLogin.text.toString(),
                        binding.edtPasswordLogin.text.toString()
                    ).observe(this, Observer { user ->
                        user?.let { user ->
                            sharedPreference.saveData("USER", user.email)
                            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                            finish()
                        } ?: run {
                            Toast.makeText(
                                this@LoginActivity,
                                "Email ou senha inválido",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
                }
            }
        }
    }

    private fun createAccount() {
        binding.txtRegisterNow.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}

