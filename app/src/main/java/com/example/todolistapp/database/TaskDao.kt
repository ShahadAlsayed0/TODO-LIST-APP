package com.example.todolistapp.database

import androidx.room.*
import com.example.todolistapp.database.data.*
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
    //get one task by id
    @Query("select * from task_table where id== :id")
    suspend fun selectTaskByID(id:Int): Task

 @Query("select * from task_table where title== :title")
    suspend fun selectTaskByTitle(title:String): Task
    @Query("select * from task_table where Tag== :tag")
    suspend fun selectTaskByTag(tag:String): Task

    @Query("select * From task_table ")
    suspend fun getAllTasksAlpha(): List<Task>

/*

    @Query("select * From TaskToTag ")
    suspend fun getAll(): List<TaskToTag>

    //Tag
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTag(tag: Tag)

    @Query("select * From Tag ")
    suspend fun getAllTags(): List<Tag>
    //get one tag by id
    @Query("select * from Tag where id== :id")
    suspend fun selectTagByID(id:Int): Tag

@Query("select * from Tag where name== :name")
    suspend fun selectTagByName(name:String): Tag

    //join
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskToTag(taskToTag: TaskToTag)

    @Query("select * from TaskToTag where tagId== :id")
    suspend fun selectJoinByTagID(id:Int): List<TaskToTag>

    @Query("select * from TaskToTag where taskId== :id")
    suspend fun selectJoinByTaskID(id:Int): List<TaskToTag>

    @Query("select * from task_table inner join Tag on Tag.id==:id ")
    suspend fun selectNEW(id:Int): List<sameTagTask>
*/

    //should add query
    //to bring specific tags

    //maybe not from this class?
    //date create
    //due date
    //a->z
    //z->a
}