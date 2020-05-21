package com.example.project1.view.activities

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.project1.R
import com.example.project1.model.Doctor
import com.example.project1.viewmodel.DoctorsViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_edit_doctor.*
import timber.log.Timber

class EditDoctorActivity : AppCompatActivity(R.layout.activity_edit_doctor){

    val doctorsViewModel: DoctorsViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        initUI()
        initListeners()
    }



    private fun initUI(){

        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)

        val hospital = sharedPreferences?.getString(SplashActivity.HOSPITAL, "") ?: ""
        val name = sharedPreferences?.getString(SplashActivity.NAME,"")?: ""
        val lastname = sharedPreferences?.getString(SplashActivity.LASTNAME,"")?: ""
        val id: Int? = sharedPreferences?.getInt(SplashActivity.ID, -1)?: -1
        val picture = sharedPreferences?.getString(SplashActivity.PICTURE,"")?: ""

        Picasso.get().load(picture).into(profilePictureEdit)
        profileName2Edit.setText(name)
        profileLastname2Edit.setText(lastname)
        profileHospital2Edit.setText(hospital)

    }

    private fun initListeners(){
        btnDiscardEdit.setOnClickListener {
            finish()
        }

        btnSaveEdit.setOnClickListener{

            val nameEt = profileName2Edit.text.toString()
            val lastnameEt = profileLastname2Edit.text.toString()
            val hospitalEt = profileHospital2Edit.text.toString()

            val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
            val hospital = sharedPreferences?.getString(SplashActivity.HOSPITAL, "") ?: ""
            val name = sharedPreferences?.getString(SplashActivity.NAME,"")?: ""
            val lastname = sharedPreferences?.getString(SplashActivity.LASTNAME,"")?: ""

            if(nameEt.isEmpty() || lastnameEt.isEmpty() || hospitalEt.isEmpty()){
                Toast.makeText(this, "Sva polja moraju biti popunjena.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }



            val id: Int? = sharedPreferences?.getInt(SplashActivity.ID, -1)?: -1

            /*var doctor:Doctor? = Doctor(name,lastname,hospital)
             doctorsViewModel.setDoctor(doctor)
            doctor = doctorsViewModel.getDoctor().value*/


            /*if(doctor == null) {
                Toast.makeText(this, "Nepredvidjena greska. Pokusajte ponovo.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }*/

            //doctorsViewModel.changeDoctor(nameEt, lastnameEt, hospitalEt, doctor)

            val editor = getSharedPreferences(packageName, Context.MODE_PRIVATE).edit()
            editor.putString(SplashActivity.NAME, nameEt)
            editor.putString(SplashActivity.LASTNAME, lastnameEt)
            editor.putString(SplashActivity.HOSPITAL, hospitalEt)
            editor.commit()
            finish()

        }

    }



}