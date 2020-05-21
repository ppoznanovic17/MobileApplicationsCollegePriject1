package com.example.project1.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import com.example.project1.model.Patient

class ListDiffCallback: DiffUtil.ItemCallback<Patient>() {

    override fun areItemsTheSame(oldItem: Patient, newItem: Patient): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Patient, newItem: Patient): Boolean {

        return oldItem.name == oldItem.name &&
                oldItem.lastname == oldItem.lastname &&
                oldItem.comingState == oldItem.comingState &&
                oldItem.currentState == oldItem.currentState &&
                oldItem.comeDate == oldItem.comeDate &&
                oldItem.hospitalisationDate == newItem.hospitalisationDate &&
                oldItem.releasedDate == newItem.releasedDate &&
                oldItem.hospital == newItem.hospital

    }
}