package com.example.project1.view.activities

import android.os.Bundle
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter

import com.example.project1.R
import com.example.project1.view.viewpager.BottomNavPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init(){
        initViewPager()
        initNavigation()

    }

    private fun initViewPager(){
        viewPager.adapter = BottomNavPagerAdapter(supportFragmentManager)
    }

  private fun initNavigation(){
      bottomMenu.setOnNavigationItemSelectedListener {
          when(it.itemId){
              R.id.state1 -> {
                  viewPager.setCurrentItem(BottomNavPagerAdapter.FRAGMENT_1_STATE, false)
              }
              R.id.newPatient2 -> {
                  viewPager.setCurrentItem(BottomNavPagerAdapter.FRAGMENT_2_NEW_PATIENT, false)
              }
              R.id.list3 -> {
                  viewPager.setCurrentItem(BottomNavPagerAdapter.FRAGMENT_3_LIST, false)
              }
              R.id.profile4 -> {
                  viewPager.setCurrentItem(BottomNavPagerAdapter.FRAGMENT_4_PROFILE, false)
              }
          }
          return@setOnNavigationItemSelectedListener true
      }
  }


}