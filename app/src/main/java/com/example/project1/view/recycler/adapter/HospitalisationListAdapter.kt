package com.example.project1.view.recycler.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.project1.R
import com.example.project1.model.Patient
import com.example.project1.view.recycler.diff.ListDiffCallback
import com.example.project1.view.recycler.viewholder.HospitalisationListViewHolder


class HospitalisationListAdapter(private val onPatientStateBtnClicked: (Patient) -> Unit,
                                 private val onReleaseBtnClicked: (Patient) -> Unit)
    : ListAdapter<Patient, HospitalisationListViewHolder>(ListDiffCallback()){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalisationListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(R.layout.item_hospital_list, parent, false)
        return HospitalisationListViewHolder(containerView,{
            val patient = getItem(it)
            onPatientStateBtnClicked.invoke(patient)
        },
            {
                val patient = getItem(it)
                onReleaseBtnClicked.invoke(patient)
            })
    }

    override fun onBindViewHolder(holder: HospitalisationListViewHolder, position: Int) {
        val patient = getItem(position)
        holder.bind(patient)
    }
}