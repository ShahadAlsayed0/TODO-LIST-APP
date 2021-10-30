package com.example.todolistapp.ui.main

import android.content.Context
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.SharedViewModel
import com.example.todolistapp.database.model.Task
import com.example.todolistapp.getCurrentDate
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

class Adapter(
    private var tasksList: List<Task>,
    private val viewModel: SharedViewModel,
    private val context: Context,
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
        holder.checkbox.isChecked = task.completed

        checkItem(task, holder)

        holder.checkbox.setOnClickListener {
            if (holder.checkbox.isChecked) {
                viewModel.updateState(true, task.id)
                holder.itemlayout.background =
                    context.resources.getDrawable(R.drawable.item_completed)
            }
            if (!holder.checkbox.isChecked) {
                viewModel.updateState(false, task.id)
                holder.itemlayout.setBackgroundColor(context.resources.getColor(R.color.white))
            }
        }
        holder.itemView.setOnClickListener {
            viewModel.taskLive.postValue(task)
        }

    }


    override fun getItemCount(): Int {
        return tasksList.size
    }

    private fun checkItem(task: Task, holder: ItemAdapter) {
        if (!task.dueDate.isNullOrEmpty()) {//in the start

           val duedate=holder.dDateTextView.text.toString()
            val currentdate= getCurrentDate()
            if (holder.dDateTextView.text.toString() < getCurrentDate() && !holder.checkbox.isChecked) {
                holder.checkbox.isEnabled = false
                holder.itemlayout.background =
                    context.resources.getDrawable(R.drawable.item_overdue)
                holder.dDateTextView.setTextColor(context.resources.getColor(R.color.red))
            }
        }
        if (holder.checkbox.isChecked) {
            holder.itemlayout.background = context.resources.getDrawable(R.drawable.item_completed)
        }
    }

    inner class ItemAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titleTextView: TextView = itemView.findViewById(R.id.item_title)
        val dDateTextView: TextView = itemView.findViewById(R.id.item_due_date)
        val tagsTextView: TextView = itemView.findViewById(R.id.detailtags)
        val checkbox: CheckBox = itemView.findViewById(R.id.itemcheckBox)
        val itemlayout: LinearLayout = itemView.findViewById(R.id.itemLayout)


    }
}
