package com.example.todolistapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.database.Repo
import com.example.todolistapp.database.model.Task
import kotlinx.coroutines.launch

class SharedViewModel(context: Application) : AndroidViewModel(context) {
    private val repo = Repo(context)

    var taskLive = MutableLiveData<Task>()


    fun selectTaskByTag(tag: String): MutableLiveData<Task> {
        val task = MutableLiveData<Task>()
        viewModelScope.launch {
            task.postValue(repo.selectTaskByTag(tag))
        }
        return task
    }

    fun selectTaskByTitle(title: String): MutableLiveData<Task> {
        val task = MutableLiveData<Task>()
        viewModelScope.launch {
            task.postValue(repo.selectTaskByTitle(title))
        }
        return task
    }

    fun insertTask(task: Task) = viewModelScope.launch {
        repo.insertTask(task)
    }

    fun getAllTasks(): MutableLiveData<List<Task>> {
        val tasks = MutableLiveData<List<Task>>()
        viewModelScope.launch {
            tasks.postValue(repo.getAllTasks())
        }
        return tasks
    }

    fun selectTaskByID(id: Int): MutableLiveData<Task> {
        val task = MutableLiveData<Task>()
        viewModelScope.launch {
            task.postValue(repo.selectTaskByID(id))
        }
        return task

    }

    fun deleteByID(id: Int) = viewModelScope.launch {
        repo.deleteByID(id)
    }

    fun delete(task: Task) = viewModelScope.launch {
        repo.delete(task)
    }
    fun update(task: Task) = viewModelScope.launch {
        repo.update(task)
    }
}
