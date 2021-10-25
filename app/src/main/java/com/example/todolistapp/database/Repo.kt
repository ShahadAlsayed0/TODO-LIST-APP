package com.example.todolistapp.database

import android.content.Context
import com.example.todolistapp.database.data.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repo(context: Context) {
    private val appDB = AppDataBase.getAppDataBase(context)!!

    suspend fun getAllTasks(): List<Task> = withContext(Dispatchers.IO) {
        appDB.taskDao.getAllTasks()
    }

    suspend fun fillDB() = withContext(Dispatchers.IO) {
        val dataDB = appDB.taskDao.getAllTasks()
        var listOfTags = mutableListOf("TO DO")
        if (dataDB.isEmpty()) {
            for (i in 1..2) {
                listOfTags.add("Tag $i")
                val task = Task(
                  "title $i","create date $i","due date $i",false,"description ",null,listOfTags = listOfTags
                )
                appDB.taskDao.insert(task)
            }
        }
    }
}