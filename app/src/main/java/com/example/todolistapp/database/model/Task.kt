package com.example.todolistapp.database.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "task_table")
//@Entity(indices = {@Index(value = {"title"},unique = true)})
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val createDate: String,
    val dueDate: String?,
    val completed: Boolean,
    val createDescription: String?,
    val overDueDescription: String?,
    val Tag:String//new
) : Parcelable {
    constructor(
        title: String,
        createDate: String,
        dueDate: String?,
        completed: Boolean,
        createDescription: String?,
        overDueDescription: String?,
    ) : this(
        0, title,
        createDate,
        dueDate,
        completed,
        createDescription,
        overDueDescription,
        "TO DO"//new
    )
    constructor(
        title: String,
        createDate: String,
        dueDate: String?,
        completed: Boolean,
        createDescription: String?,
        overDueDescription: String?,
        Tag: String
    ) : this(
        0, title,
        createDate,
        dueDate,
        completed,
        createDescription,
        overDueDescription,
        Tag//new
    )

}
/*

@Entity
data class Tag(
    @PrimaryKey(autoGenerate = true)
     val id: Int,
     val name: String
) {
    constructor( name: String):this(0,name)
}


@Entity(primaryKeys = ["tagId", "taskId"])
data class TaskToTag(val tagId: Int, val taskId: Int)*/
