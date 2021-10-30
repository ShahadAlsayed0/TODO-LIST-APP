package com.example.todolistapp

import android.app.DatePickerDialog
import android.widget.TextView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


     fun TextView.pickDate() {
        val calendar = Calendar.getInstance()
             DatePickerDialog(this.context, { _, y, m, d ->
               this.text=  "$y-${m+1}-$d"
                 },
                 calendar.get(Calendar.YEAR),
                 calendar.get(Calendar.MONTH),
                 calendar.get(Calendar.DAY_OF_MONTH)
             )
            .show()
    }

     fun String.ifEmptyThenNull(): String? {
        return if (this.isEmpty()) null else this
    }

     fun getCurrentDate(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-d")
        return current.format(formatter)
    }

