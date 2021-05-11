package br.com.zupflix.presentation.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.zupflix.R
import br.com.zupflix.presentation.login.LoginActivity
import kotlinx.android.synthetic.main.activity_splash.appNameSplash

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        animation()
    }

    private fun animation() {
        appNameSplash.animate().apply {
            duration = 1100
            rotationYBy(360f)
        }.withEndAction {
            appNameSplash.animate().apply {
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