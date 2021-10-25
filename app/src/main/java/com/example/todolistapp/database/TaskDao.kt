package com.example.todolistapp.database

import androidx.room.*
import com.example.todolistapp.database.data.Task


@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task: Task)

    @Query("select * From task_table ")
    suspend fun getAllTasks() : List<Task>

    @Update()
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    //not needed?
    @Query("select * from task_table where createDate== :createDate")
    suspend fun selectTaskByCreDate(createDate: Int): Task
/*

    @Query("select listOfTags from task_table where createDate ==:createDate")//list of tags for one task
    suspend fun selectTaskByTag(createDate: Int): Task
*/

    //should add query
    //to bring specific tags

    //maybe not from this class?
    //date create
    //due date
    //a->z
    //z->a
}