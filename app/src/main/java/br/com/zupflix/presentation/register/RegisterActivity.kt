package br.com.zupflix.presentation.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.com.zupflix.R
import br.com.zupflix.data.database.model.User
import br.com.zupflix.databinding.ActivityRegisterBinding
import br.com.zupflix.presentation.base.BaseActivity
import br.com.zupflix.presentation.login.LoginActivity
import br.com.zupflix.presentation.register.registerviewmodel.RegisterViewModel
import br.com.zupflix.utils.viewBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterActivity : BaseActivity() {

    private val binding by viewBinding(ActivityRegisterBinding::inflate)

    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this).get(RegisterViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupToolbar(binding.toolbarMovie.toolbar, R.string.txt_toolbar_name, true)
        register()
    }

    fun register() {
        binding.buttonRegister.setOnClickListener {
            when (viewModel.isValid(
                binding.edtNameRegister,
                binding.edtEmailRegister,
                binding.edtPasswordRegister
            )) {
                0 -> Toast.makeText(
                    this@RegisterActivity,
                    "Preencha todos os campos para se cadastrar no aplicativo",
                    Toast.LENGTH_SHORT
                ).show()
                1 -> Toast.makeText(
                    this@RegisterActivity,
                    "Preencha o campo nome",
                    Toast.LENGTH_SHORT
                ).show()
                2 -> Toast.makeText(
                    this@RegisterActivity,
                    "Preencha corretamente o campo email",
                    Toast.LENGTH_SHORT
                ).show()
                3 -> Toast.makeText(
                    this@RegisterActivity,
                    "Preencha o campo senha",
                    Toast.LENGTH_SHORT
                ).show()
                else -> {
                    GlobalScope.launch {
                        viewModel.insertUser(
                            User(
                                binding.edtEmailRegister.text.toString(),
                                binding.edtNameRegister.text.toString(),
                                binding.edtPasswordRegister.text.toString(),
                                viewModel.verifyPhoneNumber(binding.edtPhoneRegister)
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