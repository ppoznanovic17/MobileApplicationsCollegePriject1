package com.example.project1.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.project1.R
import com.example.project1.viewmodel.WaitingListViewModel
import androidx.fragment.app.activityViewModels
import com.example.project1.viewmodel.HospitalisedListViewModel
import com.example.project1.viewmodel.ReleasedListViewModel
import androidx.lifecycle.Observer
import com.example.project1.view.activities.SplashActivity
import kotlinx.android.synthetic.main.fragment_state.*

class StateFragment : Fragment(R.layout.fragment_state) {

    private val waitingPatientsViewModel: WaitingListViewModel by activityViewModels()
    private val hospitalisedListViewModel: HospitalisedListViewModel by activityViewModels()
    private val releasedListViewModel: ReleasedListViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init() {
        initObservers()
    }



    fun initObservers() {
        val sharedPreferences = activity?.getSharedPreferences(activity?.packageName, Context.MODE_PRIVATE)
        val hospital = sharedPreferences?.getString(SplashActivity.HOSPITAL, "")

        if (hospital != null) {

            waitingPatientsViewModel.getPatients(hospital).observe(viewLifecycleOwner, Observer {
                stateWaitingCount.text = waitingPatientsViewModel.getPatientCount(hospital).toString()
            })

            hospitalisedListViewModel.getPatients(hospital).observe(viewLifecycleOwner, Observer {
                stateHospitalisationCount.text = hospitalisedListViewModel.getPatientCount(hospital).toString()
            })

            releasedListViewModel.getPatients(hospital).observe(viewLifecycleOwner, Observer {
                stateReleasedCount.text = releasedListViewModel.getPatientCount(hospital).toString()
            })

            stateHospital.text = hospital

        }

    }

}