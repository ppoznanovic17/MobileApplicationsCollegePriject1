package com.example.project1.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.project1.R
import com.example.project1.model.Doctor
import com.example.project1.viewmodel.DoctorsViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_splash.*
import timber.log.Timber

class LogInActivity: AppCompatActivity(R.layout.activity_login) {

    val doctorViewModel:DoctorsViewModel by viewModels()

    companion object{

        const val PIN  = 1234
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()

    }

    private fun init(){
       initListeners()
    }

    private fun initListeners(){
        loginBtn.setOnClickListener {
            val editor = getSharedPreferences(packageName, Context.MODE_PRIVATE).edit()

            val name = enterNameEt.text.toString()
            val lastname = enterLastnameEt.text.toString()
            val hospital = enterHospitalEt.text.toString()
            val pinString = enterPinEt.text.toString()

            if(pinString.length != 4){
                Toast.makeText(this, R.string.pinError, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val pin = Integer.parseInt(pinString)

            if(pin != PIN){
                Toast.makeText(this, R.string.wrongPinError, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var doctor:Doctor? = Doctor(name, lastname, hospital)

            doctorViewModel.setDoctor(doctor)

            doctor = doctorViewModel.getDoctor().value

            if(doctor!=null){
                editor.putBoolean(SplashActivity.LOGGED, true)
                editor.putString(SplashActivity.NAME, name)
                editor.putString(SplashActivity.LASTNAME, lastname)
                editor.putString(SplashActivity.HOSPITAL, hospital)
                editor.putString(SplashActivity.PICTURE, doctor!!.picture)
                editor.putInt(SplashActivity.ID, doctor!!.id)
                editor.commit()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                finish()
            }else{
                Toast.makeText(this, R.string.wrongCredentials, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

        }
    }







}