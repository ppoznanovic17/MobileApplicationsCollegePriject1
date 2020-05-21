package com.example.project1.view.fragments.ListFragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project1.R
import com.example.project1.view.activities.SplashActivity
import com.example.project1.view.recycler.adapter.ReleasedListAdapter
import com.example.project1.view.recycler.viewholder.ReleasedListViewHolder
import com.example.project1.viewmodel.ReleasedListViewModel
import kotlinx.android.synthetic.main.fragment_released_list.*
import java.util.*

class ReleasedFragment: Fragment(R.layout.fragment_released_list) {



    private val releasedListViewModel: ReleasedListViewModel by activityViewModels()

    private lateinit var releasedListAdapter: ReleasedListAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       init()
    }

    private fun init(){
        initRecycle()
        initListeners()
        initObservers()
    }

    private fun initRecycle() {
        releasedReleased.layoutManager =GridLayoutManager(context,2)
        releasedListAdapter = ReleasedListAdapter()

        releasedReleased.adapter = releasedListAdapter

    }

    private fun initObservers(){

        val sharedPreferences = activity?.getSharedPreferences(activity?.packageName, Context.MODE_PRIVATE)
        val hospital = sharedPreferences?.getString(SplashActivity.HOSPITAL, "") ?: ""
        releasedListViewModel.getPatients(hospital).observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            releasedListAdapter.submitList(it)
        })
    }

    fun initListeners(){
        releasedSearchEt.doAfterTextChanged {
            val sharedPreferences = activity?.getSharedPreferences(activity?.packageName, Context.MODE_PRIVATE)
            val hospital = sharedPreferences?.getString(SplashActivity.HOSPITAL, "") ?: ""
            releasedListViewModel.searchPatient(it.toString(), hospital)
        }
    }

}