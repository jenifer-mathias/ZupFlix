package br.com.zupflix.presentation.home.homeactivity

import android.content.Intent
import android.os.Bundle
import br.com.zupflix.R
import br.com.zupflix.databinding.ActivityHomeBinding
import br.com.zupflix.presentation.base.BaseActivity
import br.com.zupflix.presentation.home.favorite.favoriteactivity.FavoriteActivity
import br.com.zupflix.presentation.home.homepageradapter.HomePagerAdapter
import br.com.zupflix.presentation.home.profile.profileactivity.ProfileActivity
import br.com.zupflix.presentation.home.search.searchactivity.SearchActivity
import br.com.zupflix.utils.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : BaseActivity() {

    private val binding by viewBinding(ActivityHomeBinding::inflate)

    private val fragmentAdapter = HomePagerAdapter(supportFragmentManager)

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
        setContentView(binding.root)
        setupToolbar(binding.toolbarMovie.toolbar, R.string.txt_toolbar_name, false)

        binding.viewPagerMain.adapter = fragmentAdapter
        binding.tabsMain.setupWithViewPager(binding.viewPagerMain)

        val bottomNavigation: BottomNavigationView = binding.bottomNavigationView
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}


