package com.example.todolistapp.database

import androidx.room.*
import com.example.todolistapp.database.data.Tag
import com.example.todolistapp.database.data.Task
import com.example.todolistapp.database.data.TaskTag_Pair
import com.example.todolistapp.database.data.TaskToTag
import java.time.LocalDateTime


@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Query("select * From task_table ")
    suspend fun getAllTasks(): List<Task>

    @Update()
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    //not needed?
    @Query("select * from task_table where createDate== :createDate")
    suspend fun selectTaskByCreDate(createDate: LocalDateTime): Task

    @Query("select * From TaskToTag ")
    suspend fun getAll(): List<TaskToTag>

    //Tag
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTag(tag: Tag)

    @Query("select * From Tag ")
    suspend fun getAllTags(): List<Tag>


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