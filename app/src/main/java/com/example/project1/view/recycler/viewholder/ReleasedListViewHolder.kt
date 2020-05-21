package com.example.project1.view.recycler.viewholder



import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.project1.model.Patient
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_hospital_list.view.*
import kotlinx.android.synthetic.main.item_released_list.view.*
import java.text.SimpleDateFormat

class ReleasedListViewHolder(override val containerView: View)
                                    : RecyclerView.ViewHolder(containerView), LayoutContainer {



    fun bind(p: Patient){
        Picasso.get().load(p.picture).into(containerView.releasedImageIv)
        containerView.releasedNameTv.text = p.name
        containerView.releasedLastnameTv.text = p.lastname


        val sdf = SimpleDateFormat("dd.MM.yyyy")
        val date: String = sdf.format(p.releasedDate)
        containerView.releasedDateTv.text = "Otpusten: $date"


    }

}