package br.com.zupflix.presentation.home.homeactivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import br.com.zupflix.R
import br.com.zupflix.presentation.base.BaseActivity
import br.com.zupflix.presentation.home.favorite.favoriteactivity.FavoriteActivity
import br.com.zupflix.presentation.home.homepageradapter.HomePagerAdapter
import br.com.zupflix.presentation.home.profile.profileactivity.ProfileActivity
import br.com.zupflix.presentation.home.search.searchactivity.SearchActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*

class HomeActivity : BaseActivity() {

    val fragmentAdapter = HomePagerAdapter(supportFragmentManager)

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.btProfile -> {
                    startActivity(Intent(this@HomeActivity, ProfileActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.btSearch -> {
                    startActivity(Intent(this@HomeActivity, SearchActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.btFavorite -> {
                    startActivity(Intent(this@HomeActivity, FavoriteActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                else -> false
            }

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupToolbar(toolbarMovie, R.string.txt_toolbar_name, false)

        viewPagerMain.adapter = fragmentAdapter
        tabsMain.setupWithViewPager(viewPagerMain)

        val bottonNavigation: BottomNavigationView = bottomNavigationView
        bottonNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }
}


