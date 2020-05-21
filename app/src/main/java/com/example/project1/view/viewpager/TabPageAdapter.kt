package com.example.project1.view.viewpager

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.project1.view.fragments.ListFragments.HospitalisedFragment
import com.example.project1.view.fragments.ListFragments.ReleasedFragment
import com.example.project1.view.fragments.ListFragments.WaitingFragment

class TabPageAdapter (fragmentManager: FragmentManager, private var context: Context?) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private const val ITEM_COUNT = 3
        const val FRAGMENT_1 = 0
        const val FRAGMENT_2 = 1
        const val FRAGMENT_3 = 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            FRAGMENT_1 -> WaitingFragment()
            FRAGMENT_2 -> HospitalisedFragment()
            else -> ReleasedFragment()
        }
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            FRAGMENT_1 -> "Cekaonica"
            FRAGMENT_2 -> "Hospitalizovani"
            else -> "Otpusteni"
        }
    }

}