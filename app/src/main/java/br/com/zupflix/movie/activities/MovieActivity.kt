package br.com.zupflix.movie.activities

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import br.com.zupflix.R
import br.com.zupflix.base.BaseActivity
import br.com.zupflix.movie.fragments.FavoriteFragment
import br.com.zupflix.movie.fragments.SearchFragment
import br.com.zupflix.movie.fragments.HomeFragment
import kotlinx.android.synthetic.main.activity_movie.*


class MovieActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        val toolbar: Toolbar = findViewById(R.id.toolbarMovie)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        val homeFragment = HomeFragment()
        val favoriteFragment = FavoriteFragment()
        val searchFragment = SearchFragment()

        setCurrentFragment(homeFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home -> setCurrentFragment(homeFragment)
                R.id.fav -> setCurrentFragment(favoriteFragment)
                R.id.search -> setCurrentFragment(searchFragment)
            }
            true
        }
    }

        private fun setCurrentFragment(fragment: Fragment) =
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, fragment)
                commit()
            }
}
