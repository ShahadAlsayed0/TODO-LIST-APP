package com.example.todolistapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolistapp.database.model.Task


@Database(entities = [ Task::class ], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract val taskDao: TaskDao

    companion object {
        private var INSTANCE: AppDataBase? = null
        fun getAppDataBase(context: Context): AppDataBase? {
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "task-database-0"
                ).build()
            }
            return INSTANCE
        }
    }
}