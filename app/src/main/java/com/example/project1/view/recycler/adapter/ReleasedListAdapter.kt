package com.example.project1.view.recycler.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.project1.R
import com.example.project1.model.Patient
import com.example.project1.view.recycler.diff.ListDiffCallback
import com.example.project1.view.recycler.viewholder.HospitalisationListViewHolder
import com.example.project1.view.recycler.viewholder.ReleasedListViewHolder


class ReleasedListAdapter()
    : ListAdapter<Patient, ReleasedListViewHolder>(ListDiffCallback()){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReleasedListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(R.layout.item_released_list, parent, false)
        return ReleasedListViewHolder(containerView)
    }

    override fun onBindViewHolder(holder: ReleasedListViewHolder, position: Int) {
        val patient = getItem(position)
        holder.bind(patient)
    }
}