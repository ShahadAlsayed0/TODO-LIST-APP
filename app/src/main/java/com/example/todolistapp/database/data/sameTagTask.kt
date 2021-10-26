package com.example.todolistapp.database.data

data class sameTagTask(
    val id: Int,
    val title: String,
    val createDate: String,
    val dueDate: String?,
    val completed: Boolean,
    val createDescription: String?,
    val overDueDescription: String?,
)