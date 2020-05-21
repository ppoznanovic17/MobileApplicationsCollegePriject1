package com.example.project1.view.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.project1.R
import com.example.project1.model.Doctor
import com.example.project1.view.activities.EditDoctorActivity
import com.example.project1.view.activities.SplashActivity
import com.example.project1.viewmodel.DoctorsViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*
import timber.log.Timber


class ProfileFragment: Fragment(R.layout.fragment_profile) {

    private val doctorsViewModel:DoctorsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onResume() {
        super.onResume()
        initUI()
    }

    private fun init() {
        initUI()
        initListeners()
        initObservers()

    }


    private fun initListeners(){
        btnLogout.setOnClickListener {
            val editor = context?.getSharedPreferences(context?.packageName, Context.MODE_PRIVATE)?.edit()
            if (editor != null) {
                editor.putBoolean(SplashActivity.LOGGED, false)
                editor.commit()
                val intent = Intent(context, SplashActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
        }

        btnChangeProfile.setOnClickListener{
            val intent = Intent(context, EditDoctorActivity::class.java)
            startActivity(intent)
        }

    }

    fun initObservers(){
        doctorsViewModel.getDoctor().observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            Picasso.get().load(it.picture).into(profilePicture)
            profileName2.text = it.name
            profileLastname2.text = it.lastname
            profileHospital2.text = it.hospital
        })
    }

    private fun initUI(){

        val sharedPreferences = activity?.getSharedPreferences(activity?.packageName, Context.MODE_PRIVATE)
        val hospital = sharedPreferences?.getString(SplashActivity.HOSPITAL, "") ?: ""
        val name = sharedPreferences?.getString(SplashActivity.NAME,"")?: ""
        val lastname = sharedPreferences?.getString(SplashActivity.LASTNAME,"")?: ""
        val picture = sharedPreferences?.getString(SplashActivity.PICTURE,"")?: ""
        val id: Int? = sharedPreferences?.getInt(SplashActivity.ID, -1)?: -1



            Picasso.get().load(picture).into(profilePicture)
            profileName2.text = name
            profileLastname2.text = lastname
            profileHospital2.text = hospital


    }



}