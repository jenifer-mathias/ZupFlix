package br.com.zupflix.presentation.home.homepageradapter

import HomeFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import br.com.zupflix.presentation.genres.adventure.adventurefragment.AdventureFragment
import br.com.zupflix.presentation.genres.animation.animationfragment.AnimationFragment
import br.com.zupflix.presentation.genres.comedy.comedyfragment.ComedyFragment

class HomePagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int):
            Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> AdventureFragment()
            2 -> AnimationFragment()
            3 -> ComedyFragment()
            else -> HomeFragment()

        }
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Populares"
            1 -> "Aventura"
            2 -> "Animação"
            3 -> "Comédia"
            else -> null
        }
    }
}

