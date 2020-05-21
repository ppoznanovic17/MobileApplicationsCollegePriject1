package com.example.project1.view.recycler.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.project1.R
import com.example.project1.model.Patient
import com.example.project1.view.recycler.diff.ListDiffCallback
import com.example.project1.view.recycler.viewholder.WaitingListViewHolder

class WaitingListAdapter(private val onBtnHealthyClicked: (Patient) -> Unit,
                         private val onBtnHospitalisationClicked: (Patient) -> Unit)
    : ListAdapter<Patient, WaitingListViewHolder>(ListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaitingListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(R.layout.item_waiting_list, parent, false)
        return WaitingListViewHolder(containerView,{
            val patient = getItem(it)
            onBtnHealthyClicked.invoke(patient)
        },
            {
                val patient = getItem(it)
                onBtnHospitalisationClicked.invoke(patient)
            })
    }

    override fun onBindViewHolder(holder: WaitingListViewHolder, position: Int) {
        val patient = getItem(position)
        holder.bind(patient)
    }
}