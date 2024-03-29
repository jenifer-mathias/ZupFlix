package br.com.zupflix.presentation.home.homepageradapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import br.com.zupflix.presentation.home.genres.action.actionfragment.ActionFragment
import br.com.zupflix.presentation.home.genres.adventure.adventurefragment.AdventureFragment
import br.com.zupflix.presentation.home.genres.animation.animationfragment.AnimationFragment
import br.com.zupflix.presentation.home.genres.comedy.comedyfragment.ComedyFragment
import br.com.zupflix.utils.Constants.ACTION
import br.com.zupflix.utils.Constants.ADVENTURE
import br.com.zupflix.utils.Constants.ANIMATION
import br.com.zupflix.utils.Constants.COMEDY

class HomePagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int):
            Fragment {
        return when (position) {
            0 -> ActionFragment()
            1 -> AdventureFragment()
            2 -> AnimationFragment()
            3 -> ComedyFragment()
            else -> ActionFragment()

        }
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> ACTION
            1 -> ADVENTURE
            2 -> ANIMATION
            3 -> COMEDY
            else -> null
        }
    }
}

