package com.example.art_stationary.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.art_stationary.Model.*
import com.example.art_stationary.R

class ExpandAdapter(var mContext: Context, val list: MutableList<ParentCategoryModel> , val sublist : MutableList<SubCatModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if(viewType == 0){
            val rowView: View = LayoutInflater.from(parent.context).inflate(R.layout.itemcategory_expanded, parent,false)
            GroupViewHolder(rowView)
        } else if (viewType == 1) {
            val rowView: View = LayoutInflater.from(parent.context).inflate(R.layout.itemsubcategory, parent,false)
            ChildViewHolder(rowView)
        }else{
            val rowView: View = LayoutInflater.from(parent.context).inflate(R.layout.threecatlayout, parent,false)
            NestedChildViewHolder(rowView)
        }
    }

    override fun getItemCount(): Int =  if (list[0].isExpanded == true) list.size + sublist.size else { list.size }
    //
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val dataList = list[position]
        val sublist = sublist[position]
        if (dataList.type == 0) {
            holder as GroupViewHolder
            holder.apply {
                parentTV?.text = dataList.parentTitle
                parentView?.setOnClickListener {
                    expandOrCollapseParentItem(dataList,position)
                }
            }
        } else if (dataList.type == 1) {
            holder as ChildViewHolder
            holder.apply {
                val singleService = dataList.subList.first()
                childTV?.text =singleService.childTitle
                childview?.setOnClickListener {
                    expandOrCollapsechildItem(sublist,position)
                }
            }
        }
        else if (sublist.nestedtype == 2 ){
            holder as NestedChildViewHolder
            holder.apply {
                val singleService = sublist.nestedsubList.first()
                nestedchildTV?.text =singleService.nestedchildTitle
            }
        }

    }

    private fun expandOrCollapseParentItem(singleBoarding: ParentCategoryModel, position: Int) {

        if (singleBoarding.isExpanded) {
            collapseParentRow(position)
        } else {
            expandParentRow(position)
        }
    }

    private fun expandOrCollapsechildItem(singleBoarding: SubCatModel, position: Int) {
        if (singleBoarding.nestedisExpandedSub) {
            collapseChildRow(position)
        } else {
            expandChildRow(position)
        }
    }

    private fun expandChildRow(position: Int){
        val currentBoardingRow = sublist[position]
        val services = currentBoardingRow.nestedsubList
        currentBoardingRow.nestedisExpandedSub = true
        var nextPosition = position
        if(currentBoardingRow.nestedtype== 2){

            services.forEach { service ->
                val childModel = SubCatModel()
                childModel.nestedtype = 3
                val nestedList : ArrayList<ThreeCatModel> = ArrayList()
                nestedList.add(service)
                childModel.nestedsubList = nestedList
                sublist.add(++nextPosition,childModel)
            }
            notifyDataSetChanged()
        }
    }

    private fun expandParentRow(position: Int){
        val currentBoardingRow = list[position]
        val services = currentBoardingRow.subList
        currentBoardingRow.isExpanded = true
        var nextPosition = position
        if(currentBoardingRow.type== 0){

            services.forEach { service ->
                val parentModel = ParentCategoryModel()
                parentModel.type = 1
                val subList : ArrayList<SubCatModel> = ArrayList()
                subList.add(service)
                parentModel.subList = subList
                list.add(++nextPosition,parentModel)
            }
            notifyDataSetChanged()
        }
    }

    private fun collapseParentRow(position: Int){
        val currentBoardingRow = list[position]
        val services = currentBoardingRow.subList
        list[position].isExpanded = false
        if(list[position].type == 0){
            services.forEach { _ ->
                list.removeAt(position + 1)
            }
            notifyDataSetChanged()
        }
    }

    private fun collapseChildRow(position: Int){
        val currentBoardingRow = sublist[position]
        val services = currentBoardingRow.nestedsubList
        sublist[position].nestedisExpandedSub = false
        if(sublist[position].nestedtype== 2){
            services.forEach { _ ->
                sublist.removeAt(position + 1)
            }
            notifyDataSetChanged()
        }
    }



    override fun getItemViewType(position: Int): Int =
        if (list[position].isExpanded == true) sublist[position].nestedtype else { list[position].type}

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    class GroupViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        val parentTV = row.findViewById(R.id.tv_lang_name) as TextView?
        val parentView = row.findViewById(R.id.parentView) as ConstraintLayout?
    }

    class ChildViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        val childTV = row.findViewById(R.id.child_Title) as TextView?
        val childview = row.findViewById(R.id.childview) as ConstraintLayout?

    }

    class NestedChildViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        val nestedchildTV = row.findViewById(R.id.nestedchild_Title) as TextView?
    }


}