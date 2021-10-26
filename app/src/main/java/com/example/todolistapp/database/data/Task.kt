package com.example.todolistapp.database.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val title:String,
    val createDate:String,//auto generated, for it to be primary key we need it to have time in milli seconds*to be unique*
    val dueDate:String?,
    val completed:Boolean,
    val createDescription:String?,
    val overDueDescription:String?,
   // val tagId:Int =0//we don't need this?//val listOfTags:MutableList<String>?// = mutableListOf("TO DO"),//we should add "TO DO" on every task but it could be removed by user
)

@Entity
data class Tag (@PrimaryKey(autoGenerate = true) val id: Int, val name:String)//each tag has id // id=0=TODOList


@Entity(primaryKeys=["tagId" ,"taskId"])
data class TaskToTag(val tagId :Int ,val taskId:Int)