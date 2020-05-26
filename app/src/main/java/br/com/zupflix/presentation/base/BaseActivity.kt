package br.com.zupflix.presentation.base

import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    protected fun setupToolbar(toolbar: androidx.appcompat.widget.Toolbar, title: Int, showBackButton: Boolean = false) {

        toolbar.title = getString(title)

        setSupportActionBar(toolbar)
        if (showBackButton) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }
}


