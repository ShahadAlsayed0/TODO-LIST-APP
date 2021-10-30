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
    val Tag:String="TO DO",
    @PrimaryKey(autoGenerate = true)
    val id: Int=0
) : Parcelable