package com.example.project1.view.fragments.ListFragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project1.R
import com.example.project1.model.Patient
import com.example.project1.view.activities.EditPatientActivity
import com.example.project1.view.activities.SplashActivity
import com.example.project1.view.recycler.adapter.HospitalisationListAdapter
import com.example.project1.viewmodel.HospitalisedListViewModel
import com.example.project1.viewmodel.ReleasedListViewModel
import kotlinx.android.synthetic.main.fragment_hospitalised_list.*
import timber.log.Timber
import java.util.*

class HospitalisedFragment: Fragment(R.layout.fragment_hospitalised_list) {

    companion object {
        const val KEY = "doctor"
        const val REQUEST_CODE = 1111
    }

    private val hospitalisedListViewModel: HospitalisedListViewModel by activityViewModels()
    private val releasedListViewModel: ReleasedListViewModel by activityViewModels()
    private lateinit var hospListAdapter: HospitalisationListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init(){
        initRecycle()
        initObservers()
        initListeners()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val patient = data?.getParcelableExtra<Patient>(KEY)
                if (patient != null) {
                    hospitalisedListViewModel.updatePatient(patient)
                    hospListAdapter.notifyDataSetChanged()
                    Toast.makeText(context, R.string.savingSuccess, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun initRecycle(){

        listHosp.layoutManager = LinearLayoutManager(context)
        hospListAdapter = HospitalisationListAdapter(
            {

                val intent = Intent(context, EditPatientActivity::class.java)
                intent.putExtra(KEY, it)
                startActivityForResult(intent, REQUEST_CODE)




            },{

                hospitalisedListViewModel.removePatient(it)

                val date = Date()
                it.releasedDate = date
                releasedListViewModel.addPatient(it)

                hospListAdapter.notifyDataSetChanged()


                Toast.makeText(context,R.string.releasedInfo, Toast.LENGTH_SHORT).show()



            })




        listHosp.adapter = hospListAdapter


    }



    fun initListeners(){
        hospSearchEt.doAfterTextChanged {
            val sharedPreferences = activity?.getSharedPreferences(activity?.packageName, Context.MODE_PRIVATE)
            val hospital = sharedPreferences?.getString(SplashActivity.HOSPITAL, "") ?: ""
            Timber.e(hospital)
            hospitalisedListViewModel.searchPatient(it.toString(), hospital)
        }
    }

    private fun initObservers(){

        val sharedPreferences = activity?.getSharedPreferences(activity?.packageName, Context.MODE_PRIVATE)
        val hospital = sharedPreferences?.getString(SplashActivity.HOSPITAL, "") ?: ""
        hospitalisedListViewModel.getPatients(hospital).observe(viewLifecycleOwner, Observer {
            hospListAdapter.submitList(it)
        })
    }



}