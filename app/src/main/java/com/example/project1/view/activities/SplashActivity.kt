package com.example.project1.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.project1.R
import com.example.project1.model.Doctor
import com.example.project1.model.Patient
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.*
import kotlin.math.log

class SplashActivity: AppCompatActivity(R.layout.activity_splash) {

    companion object {
        const val LOGGED = "logged"
        const val NAME = "name"
        const val LASTNAME = "lastName"
        const val HOSPITAL = "hospital"
        const val PICTURE = "pic"
        const val ID = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        val logged = sharedPreferences.getBoolean(LOGGED, false)

        if(!logged){
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }else{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        finish()


    }



}