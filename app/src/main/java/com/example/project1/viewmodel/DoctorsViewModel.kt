package com.example.project1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project1.model.Doctor

class DoctorsViewModel : ViewModel(){

    private var doctors: MutableLiveData<List<Doctor>> = MutableLiveData()
    private val doctorsList : MutableList<Doctor> = mutableListOf()
    private var doctor: MutableLiveData<Doctor> = MutableLiveData()



    init {
        val doc1:Doctor = Doctor("Pera", "Peric", "Covid Center")
        doc1.picture =  "https://img.medscape.com/thumbnail_library/dt_140605_serious_male_doctor_hospital_800x600.jpg"
        val doc2:Doctor = Doctor("Zika", "Zikic", "Covid Center")
        doc2.picture = "https://www.thehealthy.com/wp-content/uploads/2017/09/02_doctor_Insider-Tips-to-Choosing-the-Best-Primary-Care-Doctor_519507367_Stokkete.jpg"
        val doc3:Doctor = Doctor("Mika", "Mikic", "Dom Zdravlja Zarkovo")
        doc3.picture = "https://image.freepik.com/free-photo/front-view-doctor-with-medical-mask-posing-with-crossed-arms_23-2148445082.jpg"
        doctorsList.add(doc1)
        doctorsList.add(doc2)
        doctorsList.add(doc3)

        doctors.value = doctorsList

    }


    public fun setDoctor(d:Doctor?) {
        for(i in doctorsList){
            if(d!=null){
                if(d.name == i.name && d.lastname == i.lastname && d.hospital == i.hospital){
                    doctor.value = i
                    return
                }
            }

        }
        doctor.value = null
    }

    fun getDoctor(): LiveData<Doctor> {
        return doctor
    }

    public fun changeDoctor(name:String, lastname:String, hospital:String, d:Doctor?){
        for(i in doctorsList){
            if(d!=null){
                if(d.name == i.name && d.lastname == i.lastname && d.hospital == i.hospital){
                    i.name = name
                    i.lastname = lastname
                    i.hospital = hospital
                    doctor.value = i
                    return
                }
            }
        }
    }


}