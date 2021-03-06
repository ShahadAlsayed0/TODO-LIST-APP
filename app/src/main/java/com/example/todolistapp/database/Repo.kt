package com.example.todolistapp.database

import android.content.Context
import androidx.room.Query
import com.example.todolistapp.database.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repo(context: Context) {
    private val appDB = AppDataBase.getAppDataBase(context)!!

    suspend fun getAllTasks(): List<Task> = withContext(Dispatchers.IO) {
        appDB.taskDao.getAllTasks()
    }

    suspend fun insertTask(task: Task) = withContext(Dispatchers.IO) {
        appDB.taskDao.insertTask(task)
    }

    suspend fun selectTaskByID(id: Int): Task = withContext(Dispatchers.IO) {
        appDB.taskDao.selectTaskByID(id)
    }

    suspend fun selectTaskByTitle(title: String): Task = withContext(Dispatchers.IO) {
        appDB.taskDao.selectTaskByTitle(title)
    }

    suspend fun selectTaskByTag(tag: String): Task = withContext(Dispatchers.IO) {
        appDB.taskDao.selectTaskByTag(tag)
    }

    suspend fun deleteByID(id: Int) = withContext(Dispatchers.IO) {
        appDB.taskDao.deleteByID(id)
    }

    suspend fun delete(task: Task) = withContext(Dispatchers.IO) {
        appDB.taskDao.delete(task)
    }

    suspend fun update(task: Task) = withContext(Dispatchers.IO) {
        appDB.taskDao.update(task)
    }

    suspend fun updateState(state: Boolean, id: Int) = withContext(Dispatchers.IO) {
        appDB.taskDao.updateState(state, id)
    }

    suspend fun sortTasksASC(): List<Task> = withContext(Dispatchers.IO) {
        appDB.taskDao.sortTasksASC()
    }

    suspend fun sortTasksDESC(): List<Task> = withContext(Dispatchers.IO) {
        appDB.taskDao.sortTasksDESC()
    }
     suspend fun sortTasksCreationDate(): List<Task> = withContext(Dispatchers.IO) {
        appDB.taskDao.sortTasksCreationDate()
    }
 suspend fun sortTasksDueDate(): List<Task> = withContext(Dispatchers.IO) {
        appDB.taskDao.sortTasksDueDate()
    }

}