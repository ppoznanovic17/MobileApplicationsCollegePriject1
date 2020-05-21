package com.example.project1.view.fragments.ListFragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project1.R
import com.example.project1.view.activities.SplashActivity
import com.example.project1.view.recycler.adapter.WaitingListAdapter
import com.example.project1.view.recycler.diff.ListDiffCallback
import com.example.project1.viewmodel.HospitalisedListViewModel
import com.example.project1.viewmodel.WaitingListViewModel
import kotlinx.android.synthetic.main.fragment_waiting_list.*
import timber.log.Timber
import java.util.*

class WaitingFragment: Fragment(R.layout.fragment_waiting_list){


    private val waitingListViewModel: WaitingListViewModel by activityViewModels()
    private val hospitalisedListViewModel: HospitalisedListViewModel by activityViewModels()

    private lateinit var waitingListAdapter: WaitingListAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }



    private fun init(){
        initRecycle()
        initObservers()
        initListeners()
    }

    private fun initRecycle(){

        listWaiting.layoutManager = LinearLayoutManager(context)
        waitingListAdapter = WaitingListAdapter(
            {

                waitingListViewModel.removePatient(it)


                waitingListAdapter.notifyDataSetChanged()


                Toast.makeText(context,R.string.healthyInfo, Toast.LENGTH_SHORT).show()
                listWaiting.adapter = waitingListAdapter



            },{

                val date = Date()
                it.hospitalisationDate = date
                hospitalisedListViewModel.addPatient(it)
                waitingListViewModel.removePatient(it)

                waitingListAdapter.notifyDataSetChanged()


                Toast.makeText(context,R.string.hospInfo, Toast.LENGTH_SHORT).show()


            })




        listWaiting.adapter = waitingListAdapter


    }

    fun initListeners(){
        waitingSearchEt.doAfterTextChanged {
            val sharedPreferences = activity?.getSharedPreferences(activity?.packageName, Context.MODE_PRIVATE)
            val hospital = sharedPreferences?.getString(SplashActivity.HOSPITAL, "") ?: ""
            Timber.e(hospital)
            waitingListViewModel.searchPatient(it.toString(), hospital)
        }
    }

    private fun initObservers(){

        val sharedPreferences = activity?.getSharedPreferences(activity?.packageName, Context.MODE_PRIVATE)
        val hospital = sharedPreferences?.getString(SplashActivity.HOSPITAL, "") ?: ""
         waitingListViewModel.getPatients(hospital.trim()).observe(viewLifecycleOwner, Observer {
             waitingListAdapter.submitList(it)
            })
    }





}