package com.example.todolistapp.add

import android.app.Application
import androidx.lifecycle.*
import com.example.todolistapp.database.Repo
//import com.example.todolistapp.database.data.Tag
import com.example.todolistapp.database.data.Task
//import com.example.todolistapp.database.data.TaskToTag
import com.example.todolistapp.database.data.sameTagTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddTaskViewModel(context: Application) : AndroidViewModel(context) {
    private val repo = Repo(context)
  //  private val toDoTag = Tag(-1, "TO DO")

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
    }

    fun insertTaskANDTag(task: Task, tag: Tag): LiveData<TaskToTag> {
        val taskWithTag = MutableLiveData<TaskToTag>()
        val taskOb = MutableLiveData<Task>()
        val tagOb = MutableLiveData<Tag>()
        viewModelScope.launch {
            repo.insertTask(task)
            repo.insertTag(tag)
            taskOb.postValue(repo.selectTaskByTitle(task.title))
            tagOb.postValue(repo.selectTagByName(tag.name))
            taskOb.value?.let {
                tagOb.value?.let { it1 ->
                    taskWithTag.postValue(
                        repo.insertTaskToTag(
                            it1.id,
                            it.id
                        )
                    )
                }
            }
            taskOb.value?.let { repo.insertTaskToTag(toDoTag.id, it.id) } //default to do tag

        }
        return taskWithTag
    }*/
}