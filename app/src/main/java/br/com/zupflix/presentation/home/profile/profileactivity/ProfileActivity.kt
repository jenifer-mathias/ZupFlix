package br.com.zupflix.presentation.home.profile.profileactivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.zupflix.R
import br.com.zupflix.data.utils.SharedPreference
import br.com.zupflix.databinding.ActivityProfileBinding
import br.com.zupflix.presentation.base.BaseActivity
import br.com.zupflix.presentation.home.profile.profileviewmodel.ProfileViewModel
import br.com.zupflix.presentation.login.LoginActivity
import br.com.zupflix.utils.Constants.EMAIL
import br.com.zupflix.utils.Constants.NAME
import br.com.zupflix.utils.Constants.PASSWORD
import br.com.zupflix.utils.Constants.PHONE_NUMBER
import br.com.zupflix.utils.Constants.USER
import br.com.zupflix.utils.viewBinding

class ProfileActivity : BaseActivity() {

    private val binding by viewBinding(ActivityProfileBinding::inflate)

    private val viewModel: ProfileViewModel by lazy {
        ViewModelProvider(this).get(ProfileViewModel::class.java)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupToolbar(binding.toolbarMovie.toolbar, R.string.txt_perfil, true)

        val sharedPreference = SharedPreference(this)
        sharedPreference.getData(USER)?.let { email ->
            viewModel.getUserByEmail(email).observe(this, Observer { user ->
                user?.let {
                    binding.txtNameProfile.text = NAME.plus(it.name)
                    binding.txtEmailProfile.text = EMAIL.plus(it.email)
                    binding.txtPasswordProfile.text = PASSWORD.plus(it.password)
                    binding.txtPhoneProfile.text = PHONE_NUMBER.plus(it.phone)
                }
            })
        }

        binding.buttonLogout.setOnClickListener {
            startActivity(Intent(this@ProfileActivity, LoginActivity::class.java))
            finish()
        }
    }


}