package com.example.todolistapp.database.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "task_table")
data class Task(
    val title:String,
    @PrimaryKey
    val createDate:String,//auto generated, for it to be primary key we need it to have time in milli seconds*to be unique*
    val dueDate:String?,
    val completed:Boolean,
    val createDescription:String?,
    val overDueDescription:String?,
    val listOfTags:MutableList<String>?// = mutableListOf("TO DO"),//we should add "TO DO" on every task but it could be removed by user

)