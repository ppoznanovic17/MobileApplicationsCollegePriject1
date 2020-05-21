package com.example.project1.model

class Doctor(public var name: String,public var lastname: String,public var hospital: String) {


    val id:Int
    var picture:String = ""

    init {
        this.id = cnt
        cnt++

    }

    companion object{
        var cnt:Int = 1
    }

    override fun equals(other: Any?): Boolean {
        if(other is Doctor){
            other as Doctor
            if(this.name == other.name && this.lastname == other.lastname && this.hospital == other.hospital)
                return true
        }
        return false
    }

    override fun toString(): String {
        return this.name + " " + this.lastname + " (" + this.hospital + ")"
    }




}