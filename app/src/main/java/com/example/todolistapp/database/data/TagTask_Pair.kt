package com.example.todolistapp.database.data

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class TagTask_Pair (
    @Embedded
    val tag: Tag,
    @Relation(
        parentColumn = "id",
        entity = Task::class,
        entityColumn = "id",
        associateBy = Junction(
            value = TaskToTag::class,
            parentColumn = "TagId",
            entityColumn = "TaskId"
        )
    )
    val Tasks :List<Task>

)
