package com.example.todolistapp.add

import android.app.DatePickerDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.findNavController
import com.example.todolistapp.R
import com.example.todolistapp.database.data.Tag
import com.example.todolistapp.database.data.Task
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

class AddTaskFragment : Fragment() {
    private lateinit var title: EditText
    private lateinit var description: EditText
    private lateinit var tag: EditText
    private lateinit var datepick: ImageButton
    private lateinit var selectedDate: TextView
    private lateinit var addTask: Button
    private lateinit var cancel: Button

    private lateinit var viewModel: AddTaskViewModel
    private var TaskID: Int = 0
    private var TagID: Int = 0

    companion object {
        fun newInstance() = AddTaskFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_task_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddTaskViewModel::class.java)
        findView(view)

        datepick.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                view.context,
                { _, y, m, d ->
                    selectedDate.text = "$d/$m/$y"
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
                .show()
        }

        cancel.setOnClickListener {
            view.findNavController().navigate(R.id.action_addTaskFragment_to_mainFragment)

        }

        addTask.setOnClickListener {
            if (title.text.isNotEmpty()) {

                // ${LocalDateTime.now( ZoneId.of(ZoneId.systemDefault().id))}
                // ${LocalDateTime.now(TimeZone.getDefault().toZoneId())}
               /* Toast.makeText(
                    view.context,
                    " result : ${LocalDateTime.now(TimeZone.getDefault().toZoneId())} ",
                    Toast.LENGTH_LONG
                ).show()*/

                //  viewModel.insertTask()
                //  viewModel.insertTag()
               // viewModel.insertTaskToTag(-1, 0)


            } else {
                Toast.makeText(view.context, "Must Enter a Title", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun addTag():Tag? {
       return if (tag.text.isNotEmpty()) {
             Tag(TagID, tag.text.toString())
        }else null
    }

    private fun addTask():Task {
        return Task(
            TaskID,
            title.text.toString(),
            getCurrentDate(),
            ifEmptyThenNull(selectedDate.text.toString()),
            false,
            ifEmptyThenNull(description.text.toString()),
            null,
        )
    }

    private fun ifEmptyThenNull(text: String): String? {
        return if (text.isEmpty()) {
            null
        } else {
            text
        }
    }

    fun getCurrentDate(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        //Toast.makeText(view.context," Date : $formatted ",Toast.LENGTH_SHORT).show()
        return current.format(formatter)
    }

    private fun findView(view: View) {
        title = view.findViewById(R.id.addTitle)
        description = view.findViewById(R.id.addDescription)
        tag = view.findViewById(R.id.addTag)
        selectedDate = view.findViewById(R.id.addDueDate)
        datepick = view.findViewById(R.id.addDatePick)
        addTask = view.findViewById(R.id.addpageaddbtn)
        cancel = view.findViewById(R.id.addPageCancelbtn)
    }

}