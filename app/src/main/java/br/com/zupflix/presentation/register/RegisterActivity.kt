package br.com.zupflix.presentation.register

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.zupflix.R
import br.com.zupflix.data.database.model.User
import br.com.zupflix.presentation.login.LoginActivity
import br.com.zupflix.presentation.register.registeractivityviewmodel.RegisterActivityViewModel
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    private val viewModel: RegisterActivityViewModel by lazy {
        ViewModelProvider(this).get(RegisterActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register()
    }

    fun register() {
        buttonRegister.setOnClickListener {
                when (isValid()) {
                    0 -> Toast.makeText(this@RegisterActivity, "Preencha os campos nome, email e senha para se cadastrar no aplicativo", Toast.LENGTH_SHORT).show()
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
                                edtPhoneRegister.text.toString()
                            )
                        )
                    }
                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                        finish()
                }
            }
        }
    }

    fun isValid(): Int {
        return if (TextUtils.isEmpty(edtNameRegister.text.toString()) && TextUtils.isEmpty(edtEmailRegister.text.toString()) && TextUtils.isEmpty(edtPasswordRegister.text.toString())) {
            return 0
        } else if (TextUtils.isEmpty(edtNameRegister.text.toString())) {
            return 1
        } else if (!Patterns.EMAIL_ADDRESS.matcher(edtEmailRegister.text.toString()).matches() || TextUtils.isEmpty(edtEmailRegister.text.toString())) {
            return 2
        } else if (TextUtils.isEmpty(edtPasswordRegister.text.toString())) {
            return 3
    } else -1
}

}