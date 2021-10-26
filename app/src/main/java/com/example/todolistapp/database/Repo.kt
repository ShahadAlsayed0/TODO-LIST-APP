package com.example.todolistapp.database

import android.content.Context
//import com.example.todolistapp.database.data.Tag
import com.example.todolistapp.database.data.Task
//import com.example.todolistapp.database.data.TaskToTag
import com.example.todolistapp.database.data.sameTagTask
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
    suspend fun selectTaskByID(id:Int): Task  = withContext(Dispatchers.IO) {
        appDB.taskDao.selectTaskByID(id)
    }
    suspend fun selectTaskByTitle(title:String): Task = withContext(Dispatchers.IO){
        appDB.taskDao.selectTaskByTitle(title)
    }
    suspend fun selectTaskByTag(tag:String): Task =  withContext(Dispatchers.IO) {
        appDB.taskDao.selectTaskByTag(tag)
    }


    /*suspend fun getAllTags(): List<Tag> = withContext(Dispatchers.IO) {
        appDB.taskDao.getAllTags()
    }

    suspend fun getAll(): List<TaskToTag> = appDB.taskDao.getAll()



    suspend fun insertTag(tag: Tag) = withContext(Dispatchers.IO) {
        //  val tag1= Tag(-1,"TO DO")
        appDB.taskDao.insertTag(tag)
    }
    suspend fun selectNEW(id:Int): List<sameTagTask> = withContext(Dispatchers.IO) {
        appDB.taskDao.selectNEW(id)
    }


    suspend fun insertTaskToTag(tagId: Int, taskId: Int):TaskToTag {
        val taskToTag = TaskToTag(tagId, taskId)
        appDB.taskDao.insertTaskToTag(taskToTag)
        return taskToTag
    }

    suspend fun selectJoinByTagID(id: Int): List<TaskToTag> = withContext(Dispatchers.IO) {
        appDB.taskDao.selectJoinByTagID(id)
    }


    suspend fun selectJoinByTaskID(id: Int): List<TaskToTag> = withContext(Dispatchers.IO) {
        appDB.taskDao.selectJoinByTaskID(id)
    }

    suspend fun selectTagByID(id:Int): Tag  = withContext(Dispatchers.IO) {
        appDB.taskDao.selectTagByID(id)
    }


    suspend fun selectTagByName(name:String): Tag = withContext(Dispatchers.IO){
        appDB.taskDao.selectTagByName(name)
    }*/


}