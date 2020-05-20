package br.com.zupflix.presentation.home.homeactivity

import android.content.Intent
import android.os.Bundle
import br.com.zupflix.R
import br.com.zupflix.presentation.base.BaseActivity
import br.com.zupflix.presentation.home.homepageradapter.HomePagerAdapter
import br.com.zupflix.presentation.home.profile.profileactivity.ProfileActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    val fragmentAdapter = HomePagerAdapter(supportFragmentManager)

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.btProfile -> {
                    startActivity(Intent(this@HomeActivity, ProfileActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                else -> false
            }

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        viewPagerMain.adapter = fragmentAdapter
        tabsMain.setupWithViewPager(viewPagerMain)

        val bottonNavigation: BottomNavigationView = bottomNavigationView
        bottonNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }
}


