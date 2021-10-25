package com.example.todolistapp.database.typeconverters

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.*


class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun fromList(tags : List<String>) = Json.encodeToString(tags)

    @TypeConverter
    fun fromString(tags: String) = Json.decodeFromString<List<String>>(tags)
}