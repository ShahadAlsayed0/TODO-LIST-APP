package com.example.todolistapp.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.database.data.Task

class Adapter(private val tasksList: List<Task>) : RecyclerView.Adapter<ItemAdapter>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycle_view_item, parent, false)
        return ItemAdapter(view)
    }

    override fun onBindViewHolder(holder: ItemAdapter, position: Int) {
        val task = tasksList[position]
        holder.titleTextView.text = task.title
     //   holder.cDateTextView.text = task.createDate
        holder.dDateTextView.text = task.dueDate
      //  holder.completedTextView.text = task.completed.toString()
      //  holder.cDescriptionTextView.text = task.createDescription
      //  holder.oDDescriptionTextView.text = task.overDueDescription
       /* var strTags = ""
        task.listOfTags.forEach {
            strTags += "$it, "
        }*/

        holder.tagsTextView.text = task.Tag

    }

    override fun getItemCount(): Int {
        return tasksList.size
    }
}

class ItemAdapter(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    val titleTextView: TextView = itemView.findViewById(R.id.item_title)
    //val cDateTextView: TextView = itemView.findViewById(R.id.item_creation_date)
    val dDateTextView: TextView = itemView.findViewById(R.id.item_due_date)
    //val completedTextView: TextView = itemView.findViewById(R.id.item_completed)
   // val cDescriptionTextView: TextView = itemView.findViewById(R.id.item_Description_on_create)
   // val oDDescriptionTextView: TextView = itemView.findViewById(R.id.item_Description_over_due)
    val tagsTextView: TextView = itemView.findViewById(R.id.item_tags)

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        Toast.makeText(itemView.context, "${titleTextView.text} clicked", Toast.LENGTH_SHORT).show()
    }
}