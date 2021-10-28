package com.example.todolistapp.database

import android.content.Context
//import com.example.todolistapp.database.data.Tag
import com.example.todolistapp.database.model.Task
//import com.example.todolistapp.database.data.TaskToTag
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repo(context: Context) {
    private val appDB = AppDataBase.getAppDataBase(context)!!

    suspend fun getAllTasks(): List<Task> = withContext(Dispatchers.IO) {
        appDB.taskDao.getAllTasks()
    }

    suspend fun insertTask(task: Task) = withContext(Dispatchers.IO) {
        appDB.taskDao.insertTask(task)
        /*    val task1 = Task(
                0,"do my homework", "22/12/2021", null, false, "gotta add title to it", null,)

            val task2 = Task(
                1,"eat breakfast", "21/12/2021", null, false, "gotta add title to it", null,)
            appDB.taskDao.insertTask(task2)*/

        /* if (dataDB.isEmpty()) {
             for (i in 1..2) {
                 listOfTags.add("Tag $i")
                 val task = Task(
                   "title $i","create date $i","due date $i",false,"description ",null,listOfTags = listOfTags
                 )
                 appDB.taskDao.insert(task)
             }
         }*/
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
    suspend fun updateState(state:Boolean,id:Int) = withContext(Dispatchers.IO) {
        appDB.taskDao.updateState(state,id)
    }
}