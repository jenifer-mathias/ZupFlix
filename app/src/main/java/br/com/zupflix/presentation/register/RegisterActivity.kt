package br.com.zupflix.presentation.register

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.zupflix.R
import br.com.zupflix.data.database.model.User
import br.com.zupflix.presentation.login.LoginActivity
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

        buttonRegister.setOnClickListener {
            GlobalScope.launch {
                viewModel.insertUser(
                    User(
                        edtEmailRegister.text.toString(),
                        edtNameRegister.text.toString(),
                        edtPasswordRegister.text.toString(),
                        edtPhoneRegister.text.toString()
                    )
                )
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                finish()
            }
        }
    }
}
