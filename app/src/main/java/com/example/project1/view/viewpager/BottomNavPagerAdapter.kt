package com.example.project1.view.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.project1.R
import com.example.project1.view.fragments.ListFragment
import com.example.project1.view.fragments.NewPatientFragment
import com.example.project1.view.fragments.ProfileFragment
import com.example.project1.view.fragments.StateFragment

class BottomNavPagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


    companion object{
        private const val ITEM_COUNT = 4
        const val FRAGMENT_1_STATE = 0
        const val FRAGMENT_2_NEW_PATIENT = 1
        const val FRAGMENT_3_LIST = 2
        const val FRAGMENT_4_PROFILE = 3
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            FRAGMENT_1_STATE -> StateFragment()
            FRAGMENT_2_NEW_PATIENT -> NewPatientFragment()
            FRAGMENT_3_LIST -> ListFragment()
            else -> ProfileFragment()
        }
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            FRAGMENT_1_STATE -> "1"
            FRAGMENT_2_NEW_PATIENT -> "2"
            FRAGMENT_3_LIST -> "3"
            else -> "4"
        }
    }

}