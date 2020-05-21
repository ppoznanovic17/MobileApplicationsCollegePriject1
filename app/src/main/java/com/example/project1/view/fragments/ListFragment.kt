package com.example.project1.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.project1.R
import com.example.project1.view.viewpager.TabPageAdapter
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment: Fragment(R.layout.fragment_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }


    private fun init(){
        initTabs()
    }

    private fun initTabs(){
        viewPagerTab.adapter = TabPageAdapter(childFragmentManager, activity)
        tabLayout.setupWithViewPager(viewPagerTab)
    }

}