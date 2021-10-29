package com.example.todolistapp.database.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "task_table")
data class Task(
    val title: String,
    val createDate: String,
    val dueDate: String?,
    val completed: Boolean,
    val createDescription: String?,
    val overDueDescription: String?,
    val Tag:String="TO DO",
    @PrimaryKey(autoGenerate = true)
    val id: Int=0
) : Parcelable
/*

@Entity
data class Tag(
    @PrimaryKey(autoGenerate = true)
     val id: Int,
     val name: String
) {
    constructor( name: String):this(0,name)
}


@Entity(primaryKeys = ["tagId", "taskId"])
data class TaskToTag(val tagId: Int, val taskId: Int)*/
