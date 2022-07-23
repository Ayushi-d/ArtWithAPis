package com.example.art_stationary.Model

data class SubCatModel(
    val childTitle:String?=null,
    var nestedtype:Int = 1,
    var nestedsubList : MutableList<ThreeCatModel> = ArrayList(),
    var nestedisExpandedSub:Boolean = false
)