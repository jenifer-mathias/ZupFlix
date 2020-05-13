package br.com.zupflix.presentation.home.homeactivity

import android.os.Bundle
import br.com.zupflix.R
import br.com.zupflix.presentation.base.BaseActivity
import br.com.zupflix.presentation.home.homepageradapter.HomePagerAdapter
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

        val fragmentAdapter = HomePagerAdapter(supportFragmentManager)

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_home)

            viewPagerMain.adapter = fragmentAdapter

            tabsMain.setupWithViewPager(viewPagerMain)
        }
    }


