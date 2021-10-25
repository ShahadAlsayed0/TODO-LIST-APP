package com.example.todolistapp.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.database.data.Task
import com.example.todolistapp.database.Repo
import kotlinx.coroutines.launch

class MainViewModel(context: Application) : AndroidViewModel(context){
    private val repo = Repo(context)

    fun getAllTasks(): MutableLiveData<List<Task>> {
        val tasks = MutableLiveData<List<Task>>()
        viewModelScope.launch {
            tasks.postValue(repo.getAllTasks())
        }
        return tasks
    }

    fun fillDB()  = viewModelScope.launch {
        repo.fillDB()
    }

}