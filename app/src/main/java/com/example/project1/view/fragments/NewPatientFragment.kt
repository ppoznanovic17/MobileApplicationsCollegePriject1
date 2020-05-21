package com.example.project1.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.project1.R
import com.example.project1.model.Patient
import com.example.project1.view.activities.SplashActivity
import com.example.project1.viewmodel.WaitingListViewModel
import kotlinx.android.synthetic.main.fragment_new_patient.*
import java.util.*

class NewPatientFragment: Fragment(R.layout.fragment_new_patient) {

    val waitingListViewModel: WaitingListViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init(){
        initListeners()
    }

    fun initListeners(){
        addPatientBtn.setOnClickListener {
            if(nameEt.text.isEmpty() || lastnameEt.text.isEmpty() || conditionsEt.text.isEmpty()){
                Toast.makeText(context, R.string.errorEmpty, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {

                val sharedPreferences = activity?.getSharedPreferences(activity?.packageName, Context.MODE_PRIVATE)
                val hospital = sharedPreferences?.getString(SplashActivity.HOSPITAL, "") ?: ""

                val date:Date = Date()
                val name = nameEt.text.toString()
                val lastname = lastnameEt.text.toString()
                val conditions = conditionsEt.text.toString()
                val uuid = UUID.randomUUID()

                val patient = Patient(
                        uuid,
                        name,
                        lastname,
                        conditions,
                        conditions,
                        date,
                        null,
                        null,
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQPqVvvxpCEiuIcD1ZhWi2x-qqnffujrPKvdjrYXbxrk3hBgDTt&usqp=CAU",
                        hospital
                    )
                waitingListViewModel.addPatient(patient)
                Toast.makeText(context, R.string.successMsg, Toast.LENGTH_SHORT).show()

                nameEt.setText("")
                lastnameEt.setText("")
                conditionsEt.setText("")

                }

            }

    }


}