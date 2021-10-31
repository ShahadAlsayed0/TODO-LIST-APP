package com.example.todolistapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.database.Repo
import com.example.todolistapp.database.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    fun delete(task: Task) = viewModelScope.launch {
        repo.delete(task)
    }

    fun update(task: Task) = viewModelScope.launch {
        repo.update(task)
    }

    fun updateState(state: Boolean, id: Int) = viewModelScope.launch {
        repo.updateState(state, id)
    }

    fun sortTasksASC(): MutableLiveData<List<Task>> {
        val tasks = MutableLiveData<List<Task>>()
        viewModelScope.launch {

            tasks.postValue(repo.sortTasksASC())
        }
        return tasks
    }

    fun sortTasksDESC(): MutableLiveData<List<Task>> {
        val tasks = MutableLiveData<List<Task>>()
        viewModelScope.launch {
            tasks.postValue(repo.sortTasksDESC())
        }
        return tasks
    }
    fun sortTasksCreationDate(): MutableLiveData<List<Task>> {
        val tasks = MutableLiveData<List<Task>>()
        viewModelScope.launch {
            tasks.postValue(repo.sortTasksCreationDate())
        }
        return tasks
    }
 fun sortTasksDueDate(): MutableLiveData<List<Task>> {
        val tasks = MutableLiveData<List<Task>>()
        viewModelScope.launch {
            tasks.postValue(repo.sortTasksDueDate())
        }
        return tasks
    }

}
