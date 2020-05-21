package com.example.project1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project1.model.Patient
import java.util.*

class WaitingListViewModel: ViewModel() {

    private var patients: MutableLiveData<List<Patient>> = MutableLiveData()
    private val patientsList : MutableList<Patient> = mutableListOf()

    init{
        for(i in 1..7){
            val date:Date = Date()
            val uuid = UUID.randomUUID()
            if(i % 2 == 0){
                val p = Patient(uuid,
                    "Osoba $i",
                    "Osobic $i",
                    "Pacijent dosao sa povisenom temperaturom i crvenim grlom",
                    "Jos vise temperatura i bol u misicima",
                    date,
                    null,
                    null,
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ740vBil8G_3EkVFB4yXGqABpfZ3ILqbpONCgBaG4FJkuJCngQ&usqp=CAU",
                    "Covid Center")
                patientsList.add(p)
            }else{
                val p = Patient(uuid,
                    "Osoba $i",
                    "Osobic $i",
                    "Pacijent dosao sa povisenom temperaturom i crvenim grlom",
                    "Jos vise temperatura i bol u misicima",
                    date,
                    null,
                    null,
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ740vBil8G_3EkVFB4yXGqABpfZ3ILqbpONCgBaG4FJkuJCngQ&usqp=CAU",
                    "Dom Zdravlja Zarkovo")
                patientsList.add(p)
            }
        }
        patients.value = patientsList
    }


    fun getPatients(hospitalName:String): LiveData<List<Patient>> {
        val filteredList = patientsList.filter {
            it.hospital == hospitalName
        }
        patients.value = filteredList
        return patients
    }

    fun getPatientsList(hospitalName:String): List<Patient> {
        return patientsList.filter {
            it.hospital == hospitalName
        }
    }


    fun addPatient(p:Patient){
        patientsList.add(p)
        val listToSubmit = mutableListOf<Patient>()
        listToSubmit.addAll(getPatientsList(p.hospital))
        patients.value = listToSubmit
    }

    fun removePatient(p: Patient){
        patientsList.remove(p)
        val listToSubmit = mutableListOf<Patient>()
        listToSubmit.addAll(getPatientsList(p.hospital))
        patients.value = listToSubmit
    }


    fun searchPatient(token:String, hos:String){
        var filteredList = patientsList.filter {
            (it.name.toLowerCase().startsWith(token.toLowerCase()) || it.lastname.toLowerCase().startsWith(token.toLowerCase())) && it.hospital == hos
        }
        patients.value = filteredList
    }

    fun getPatientCount(hospitalName:String):Int{
        val filteredList = patientsList.filter {
            it.hospital == hospitalName
        }
        return filteredList.size
    }



}