package com.example.todolistapp.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.database.data.Task
import com.example.todolistapp.database.Repo
import com.example.todolistapp.database.data.Tag
import com.example.todolistapp.database.data.TaskToTag
import kotlinx.coroutines.launch

class MainViewModel(context: Application) : AndroidViewModel(context){
    private val repo = Repo(context)

    fun getAll(): MutableLiveData<List<TaskToTag>> {
        val tasks = MutableLiveData<List<TaskToTag>>()
        viewModelScope.launch {
            tasks.postValue(repo.getAll())
        }
        return tasks

    }

    fun getAllTasks(): MutableLiveData<List<Task>> {
        val tasks = MutableLiveData<List<Task>>()
        viewModelScope.launch {
            tasks.postValue(repo.getAllTasks())
        }
        return tasks
    }

    fun getAllTags(): MutableLiveData<List<Tag>> {
        val tags = MutableLiveData<List<Tag>>()
        viewModelScope.launch {
            tags.postValue(repo.getAllTags())
        }
        return tags
    }

    fun insertTask(task: Task) = viewModelScope.launch {
        repo.insertTask(task)
    }

    fun insertTag(tag: Tag) = viewModelScope.launch {
        repo.insertTag(tag)
    }

    fun insertTaskToTag(tag: Int, task: Int) = viewModelScope.launch {
        repo.insertTaskToTag(tag, task)
    }

}