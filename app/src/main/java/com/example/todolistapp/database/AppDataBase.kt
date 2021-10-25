package com.example.todolistapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todolistapp.database.data.Task
import com.example.todolistapp.database.typeconverters.Converters


@Database(entities = [Task::class,], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
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