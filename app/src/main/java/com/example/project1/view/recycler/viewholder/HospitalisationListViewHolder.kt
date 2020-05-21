package com.example.project1.view.recycler.viewholder


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.project1.model.Patient
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_hospital_list.view.*
import kotlinx.android.synthetic.main.item_waiting_list.view.*

class HospitalisationListViewHolder(override val containerView: View,
                                    private val onPatientStateBtnClicked: (Int) -> Unit,
                                    private val onReleasePatientBtnClicked: (Int) -> Unit)
                                    : RecyclerView.ViewHolder(containerView), LayoutContainer {

    init {
        containerView.hospPatientBtn.setOnClickListener {
            onPatientStateBtnClicked.invoke(adapterPosition)
        }
        containerView.hospReleaseBtn.setOnClickListener {
            onReleasePatientBtnClicked.invoke(adapterPosition)
        }
    }

    fun bind(p: Patient){
        Picasso.get().load(p.picture).into(containerView.hospImageIv)
        containerView.hospNameTv.text = p.name
        containerView.hospLastnameTv.text = p.lastname

    }

}