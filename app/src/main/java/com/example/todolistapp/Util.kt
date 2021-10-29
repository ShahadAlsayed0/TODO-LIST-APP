package com.example.todolistapp

import android.app.DatePickerDialog
import android.view.View
import android.widget.TextView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


     fun TextView.pickDate() {

        val calendar = Calendar.getInstance()

             val dpd = DatePickerDialog(this.context, { _, y, m, d ->
               this.text=  "$d/$m/$y"

                 },
                 calendar.get(Calendar.YEAR),
                 calendar.get(Calendar.MONTH),
                 calendar.get(Calendar.DAY_OF_MONTH)
             )

             dpd.show()



    }

     fun String.ifEmptyThenNull(): String? {
        return if (this.isEmpty()) null else this
    }

     fun getCurrentDate(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        //Toast.makeText(view.context," Date : $formatted ",Toast.LENGTH_SHORT).show()
        return current.format(formatter)
    }

