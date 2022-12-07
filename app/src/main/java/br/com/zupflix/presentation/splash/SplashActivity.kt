package br.com.zupflix.presentation.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.zupflix.databinding.ActivitySplashBinding
import br.com.zupflix.presentation.login.LoginActivity
import br.com.zupflix.utils.viewBinding

class SplashActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivitySplashBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        animation()
    }

    private fun animation() {
        binding.appNameSplash.animate().apply {
            duration = 1100
            rotationYBy(360f)
        }.withEndAction {
            binding.appNameSplash.animate().apply {
                duration = 700
                rotationYBy(3600f)
            }.withEndAction {
                intentLogin()
            }
        }.start()
    }

    private fun intentLogin() {
        startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        finish()
    }
}