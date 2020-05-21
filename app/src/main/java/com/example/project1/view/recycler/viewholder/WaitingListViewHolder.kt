package com.example.project1.view.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.project1.model.Patient
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_waiting_list.view.*

class WaitingListViewHolder(override val containerView: View,
                            private val onHealthyBtnClicked: (Int) -> Unit,
                            private val onHospitalisationBtnClicked: (Int) -> Unit
                            ) :RecyclerView.ViewHolder(containerView), LayoutContainer {


    init {
        containerView.waitingHealthyBtn.setOnClickListener {
            onHealthyBtnClicked.invoke(adapterPosition)
        }
        containerView.waitingHospitalisationBtn.setOnClickListener {
            onHospitalisationBtnClicked.invoke(adapterPosition)
        }
    }

    fun bind(p: Patient){
        Picasso.get().load(p.picture).into(containerView.waitingImageIv)
        containerView.waitingNameTv.text = p.name
        containerView.waitingLastnameTv.text = p.lastname
        containerView.waitingStateTv.text = p.comingState

    }

}