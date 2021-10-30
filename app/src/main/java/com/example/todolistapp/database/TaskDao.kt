package com.example.todolistapp.database

import androidx.room.*
import com.example.todolistapp.database.model.*


@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Query("select * From task_table ")
    suspend fun getAllTasks(): List<Task>

    //get one task by id
    @Query("select * from task_table where id== :id")
    suspend fun selectTaskByID(id: Int): Task

    @Query("select * from task_table where title== :title")
    suspend fun selectTaskByTitle(title: String): Task

    @Query("select * from task_table where Tag== :tag")
    suspend fun selectTaskByTag(tag: String): Task

    @Update()
    suspend fun update(task: Task)

    @Query("update task_table set completed=:state where id==:id")
    suspend fun updateState(state:Boolean,id:Int)

    @Delete
    suspend fun delete(task: Task)

    @Query("delete from task_table where id==:id")
    suspend fun deleteByID(id:Int)

    @Query("select * From task_table order by title ASC ")
    suspend fun sortTasksASC(): List<Task>

    @Query("select * From task_table order by title DESC ")
    suspend fun sortTasksDESC(): List<Task>


    @Query("select * From task_table order by dueDate ")
    suspend fun getAllTasksTime(): List<Task>


    @Query("select * From task_table where Tag=:tag ")
    suspend fun getTaskByTag(tag:String): List<Task>


    //should add query
    //to bring specific tags

    //maybe not from this class?
    //date create
    //due date
    //a->z
    //z->a
}