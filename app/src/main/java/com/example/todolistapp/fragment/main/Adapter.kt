package com.example.todolistapp.fragment.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.SharedViewModel
import com.example.todolistapp.database.model.Task
import com.example.todolistapp.getCurrentDate

class Adapter(
    private val tasksList: List<Task>,
    private val viewModel: SharedViewModel,
    private val context: Context
) :
    RecyclerView.Adapter<Adapter.ItemAdapter>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycle_view_item, parent, false)
        return ItemAdapter(view)
    }

    override fun onBindViewHolder(holder: ItemAdapter, position: Int) {
        val task = tasksList[position]

        holder.titleTextView.text = task.title
        holder.dDateTextView.text = task.dueDate
        holder.tagsTextView.text = task.Tag

//holder.itemlayout.setBackgroundColor(context.getResources().getColor(R.color.purple_200))
        if (!holder.dDateTextView.text.toString().isNullOrEmpty()) {
            if (holder.dDateTextView.text.toString() < getCurrentDate()) {
                holder.checkbox.isEnabled = !holder.checkbox.isEnabled
                holder.itemlayout.setBackgroundColor(context.resources.getColor(R.color.slight_red))
                holder.dDateTextView.setTextColor(context.resources.getColor(R.color.red))
            }
        }


        holder.checkbox.setOnClickListener {
            if (holder.checkbox.isChecked) {
                viewModel.updateState(true, task.id)
                holder.itemlayout.setBackgroundColor(context.resources.getColor(R.color.gray))
            }
            if (!holder.checkbox.isChecked) {
                viewModel.updateState(false, task.id)
                holder.itemlayout.setBackgroundColor(context.resources.getColor(R.color.white))
            }
        }


        /*  holder.checkbox.isChecked = task.completed==true

              viewModel.update(task)
  */

        /* //   holder.cDateTextView.text = task.createDate

          //  holder.completedTextView.text = task.completed.toString()
          //  holder.cDescriptionTextView.text = task.createDescription
          //  holder.oDDescriptionTextView.text = task.overDueDescription
            var strTags = ""
            task.listOfTags.forEach {
                strTags += "$it, "
            }*/

        holder.itemView.setOnClickListener {
            viewModel.taskLive.postValue(task)
        }

    }

    override fun getItemCount(): Int {
        return tasksList.size
    }


    inner class ItemAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titleTextView: TextView = itemView.findViewById(R.id.item_title)
        val dDateTextView: TextView = itemView.findViewById(R.id.item_due_date)
        val tagsTextView: TextView = itemView.findViewById(R.id.item_tags)
        val checkbox: CheckBox = itemView.findViewById(R.id.itemcheckBox)
        val itemlayout: LinearLayout = itemView.findViewById(R.id.itemLayout)
        /* val cDateTextView: TextView = itemView.findViewById(R.id.item_creation_date)
         val completedTextView: TextView = itemView.findViewById(R.id.item_completed)
          val cDescriptionTextView: TextView = itemView.findViewById(R.id.item_Description_on_create)
          val oDDescriptionTextView: TextView = itemView.findViewById(R.id.item_Description_over_due)
 */

    }
}
