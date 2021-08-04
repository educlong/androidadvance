package elong.myapplication.models.jsonArrDemo

data class Person (val Name:String, val City:String, val Country:String){
    override fun toString(): String {
        return "$Name - $City ($Country)"
    }
}