package com.example.todolistapp.testRoom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.database.Repo
//import com.example.todolistapp.database.data.Tag
import com.example.todolistapp.database.model.Task
//import com.example.todolistapp.database.data.TaskToTag
import kotlinx.coroutines.launch

class TestRoomDataViewModel(context: Application) : AndroidViewModel(context) {
    private val repo = Repo(context)

    fun selectTaskByTag(tag:String): MutableLiveData<Task> {
        val task = MutableLiveData<Task>()
        viewModelScope.launch {
            task.postValue(repo.selectTaskByTag(tag))
        }
        return task
    }
    fun selectTaskByTitle(title:String): MutableLiveData<Task> {
        val task = MutableLiveData<Task>()
        viewModelScope.launch {
            task.postValue(repo.selectTaskByTitle(title))
        }
        return task
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

    fun insertTask(task: Task) = viewModelScope.launch {
        repo.insertTask(task)
    }

/*
    fun getAll(): MutableLiveData<List<TaskToTag>> {
        val tasks = MutableLiveData<List<TaskToTag>>()
        viewModelScope.launch {
            tasks.postValue(repo.getAll())
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

    fun selectJoinByTagID(id: Int): MutableLiveData<List<TaskToTag>> {
        val joinTags = MutableLiveData<List<TaskToTag>>()
        viewModelScope.launch {
            joinTags.postValue(repo.selectJoinByTagID(id))
        }
        return joinTags

    }

    fun selectJoinByTaskID(id: Int): MutableLiveData<List<TaskToTag>> {
        val joinTask = MutableLiveData<List<TaskToTag>>()
        viewModelScope.launch {
            joinTask.postValue(repo.selectJoinByTaskID(id))
        }
        return joinTask

    }

    fun selectTagByID(id: Int): MutableLiveData<Tag> {
        val tag = MutableLiveData<Tag>()
        viewModelScope.launch {
            tag.postValue(repo.selectTagByID(id))
        }
        return tag

    }

    fun selectNEW(id: Int): MutableLiveData<List<sameTagTask>> {
        val tag = MutableLiveData<List<sameTagTask>>()
        viewModelScope.launch {
            tag.postValue(repo.selectNEW(id))
        }
        return tag
    }

    fun insertTag(tag: Tag) = viewModelScope.launch {
        repo.insertTag(tag)
    }

    fun insertTaskToTag(tag: Int, task: Int) = viewModelScope.launch {
        repo.insertTaskToTag(tag, task)
    }*/


}