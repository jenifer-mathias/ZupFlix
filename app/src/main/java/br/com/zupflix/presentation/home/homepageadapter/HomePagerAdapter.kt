package br.com.zupflix.presentation.home.homepageadapter

import HomeFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import br.com.zupflix.presentation.adventure.adventurefragment.AdventureFragment

class HomePagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int):
            Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> AdventureFragment()
            else -> HomeFragment()

        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Populares"
            1 -> "Aventura"
            else -> null
        }
    }
}

