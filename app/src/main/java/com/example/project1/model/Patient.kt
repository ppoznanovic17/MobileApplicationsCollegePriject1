package com.example.project1.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*
@Parcelize
class Patient(
    public val id: UUID,
    public var name: String,
    public var lastname: String,
    public var comingState: String,
    public var currentState: String,
    public val comeDate: Date,
    public var hospitalisationDate: Date?,
    public var releasedDate: Date?,
    public val picture: String,
    public val hospital: String
): Parcelable {


    override fun toString(): String {
        return name + " " + lastname + " " + id + " " + hospital
    }


}