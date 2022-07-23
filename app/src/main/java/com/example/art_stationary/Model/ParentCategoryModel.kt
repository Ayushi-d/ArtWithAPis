package com.example.art_stationary.Model

data class ParentCategoryModel(
    val parentTitle:String?=null,
    var type:Int = 0,
    var subList : MutableList<SubCatModel> = ArrayList(),
    var isExpanded:Boolean = false
)