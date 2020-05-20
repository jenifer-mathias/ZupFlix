package br.com.zupflix.presentation.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.zupflix.R
import br.com.zupflix.data.database.model.User
import br.com.zupflix.presentation.login.LoginActivity
import br.com.zupflix.presentation.register.registerviewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this).get(RegisterViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register()
    }

    fun register() {
        buttonRegister.setOnClickListener {
                when (viewModel.isValid(edtNameRegister, edtEmailRegister, edtPasswordRegister)) {
                    0 -> Toast.makeText(this@RegisterActivity, "Preencha todos os campos para se cadastrar no aplicativo", Toast.LENGTH_SHORT).show()
                    1 -> Toast.makeText(this@RegisterActivity, "Preencha o campo nome", Toast.LENGTH_SHORT).show()
                    2 -> Toast.makeText(this@RegisterActivity, "Preencha corretamente o campo email", Toast.LENGTH_SHORT).show()
                    3 -> Toast.makeText(this@RegisterActivity, "Preencha o campo senha", Toast.LENGTH_SHORT).show()
                    else -> {
            GlobalScope.launch {
                        viewModel.insertUser(
                            User(
                                edtEmailRegister.text.toString(),
                                edtNameRegister.text.toString(),
                                edtPasswordRegister.text.toString(),
                                viewModel.verifyPhoneNumber(edtPhoneRegister)
                            )
                        )
                    }
                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                        finish()
                }
            }
        }
    }
}