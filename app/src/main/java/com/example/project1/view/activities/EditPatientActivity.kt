package com.example.project1.view.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.project1.R
import com.example.project1.model.Patient
import com.example.project1.view.fragments.ListFragments.HospitalisedFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_edit_patient.*
import java.text.SimpleDateFormat

class EditPatientActivity : AppCompatActivity(R.layout.activity_edit_patient){

    var patient:Patient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init(){
        intent?.let {
            patient = it.getParcelableExtra(HospitalisedFragment.KEY)
        }
        initUI()
        initListeners()
    }

    private fun initUI(){
       if(patient != null){
           Picasso.get().load(patient!!.picture).into( editPatientProfilePicture)
           editPatientName.setText(patient!!.name)
           editPatientLastname.setText(patient!!.lastname)
           editPatientFirstConditionEdit.setText(patient!!.comingState)
           editPatientCurrentConditionEdit.setText(patient!!.currentState)

           val sdf = SimpleDateFormat("dd.MM.yyyy")
           val date: String = sdf.format(patient!!.comeDate)

           editPatientDate2.text = date
       }

    }

    private fun initListeners(){
        patientEditBtnDiscard.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

        patientEditBtnSave.setOnClickListener {
            val returnIntent = Intent()
            val id = patient!!.id
            val picture = patient!!.picture
            val hospital = patient!!.hospital
            val name = editPatientName.text.toString()
            val lastname = editPatientLastname.text.toString()
            val comingState = editPatientFirstConditionEdit.text.toString()
            val currentState = editPatientCurrentConditionEdit.text.toString()
            val comingDate = patient!!.comeDate
            val hospitalisationDate = patient!!.hospitalisationDate
            val releaseDate = patient!!.releasedDate



            if (name.isEmpty() || lastname.isEmpty() || comingState.isEmpty() || currentState.isEmpty()) {
                Toast.makeText(this, R.string.allPropertyFullErr, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                val edited_patient = Patient(
                    id,
                    name,
                    lastname,
                    comingState,
                    currentState,
                    comingDate,
                    hospitalisationDate,
                    releaseDate,
                    picture,
                    hospital
                )
                returnIntent.putExtra(HospitalisedFragment.KEY , edited_patient)
                setResult(Activity.RESULT_OK, returnIntent)
                finish()
            }

        }


    }

}