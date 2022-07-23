import android.content.Context
import android.provider.MediaStore.Files.FileColumns.PARENT
import android.provider.SyncStateContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.art_stationary.Model.ExpandedCategroryModel
import com.example.art_stationary.Model.SubCategoryModel
import com.example.art_stationary.R

class ExpandedCategoryAdapter(var mContext: Context, val list: MutableList<ExpandedCategroryModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if(viewType== 0){
            val rowView: View = LayoutInflater.from(parent.context).inflate(R.layout.itemcategory_expanded, parent,false)
            GroupViewHolder(rowView)
        } else {
            val rowView: View = LayoutInflater.from(parent.context).inflate(R.layout.itemsubcategory, parent,false)
            ChildViewHolder(rowView)
        }
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val dataList = list[position]
        if (dataList.type == 0) {
            holder as GroupViewHolder
            holder.apply {
                parentTV?.text = dataList.name
                parentView?.setOnClickListener {
                    expandOrCollapseParentItem(dataList,position)
                }
            }
        } else {
            holder as ChildViewHolder

            holder.apply {
                val singleService = dataList.subCategoryModels.first()
                childTV?.text =singleService.subtitle
            }
        }
    }
    private fun expandOrCollapseParentItem(singleBoarding: ExpandedCategroryModel,position: Int) {

        if (singleBoarding.expand) {
            collapseParentRow(position)
        } else {
            expandParentRow(position)
        }
    }

    private fun expandParentRow(position: Int){
        val currentBoardingRow = list[position]
        val services = currentBoardingRow.subCategoryModels
        currentBoardingRow.expand = true
        var nextPosition = position
        if(currentBoardingRow.type== 0){

            services.forEach { service ->
                val parentModel = ExpandedCategroryModel()
                parentModel.type = 1
                val subList : ArrayList<SubCategoryModel> = ArrayList()
                subList.add(service)
                parentModel.subCategoryModels = subList
                list.add(++nextPosition,parentModel)
            }
            notifyDataSetChanged()
        }
    }

    private fun collapseParentRow(position: Int){
        val currentBoardingRow = list[position]
        val services = currentBoardingRow.subCategoryModels
        list[position].expand = false
        if(list[position].type== 0){
            services.forEach { _ ->
                list.removeAt(position + 1)
            }
            notifyDataSetChanged()
        }
    }

    override fun getItemViewType(position: Int): Int = list[position].type

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    class GroupViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        val parentTV = row.findViewById(R.id.tv_lang_name) as TextView?
        val parentView = row.findViewById(R.id.parentView) as ConstraintLayout?
    }
    class ChildViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        val childTV = row.findViewById(R.id.child_Title) as TextView?

    }
}
