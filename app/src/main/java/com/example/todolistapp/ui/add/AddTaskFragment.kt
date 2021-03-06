package com.example.todolistapp.ui.add

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.findNavController
import com.example.todolistapp.*
import com.example.todolistapp.database.model.Task



class AddTaskFragment : Fragment() {
    private lateinit var title: androidx.appcompat.widget.AppCompatEditText
    private lateinit var description: androidx.appcompat.widget.AppCompatEditText
    private lateinit var tag: androidx.appcompat.widget.AppCompatEditText
    private lateinit var datepick: ImageButton
    private lateinit var selectedDate: androidx.appcompat.widget.AppCompatTextView
    private lateinit var addBtn: Button
    private lateinit var cancelBtn: Button


    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_task_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        findView(view)
        datepick.setOnClickListener {
            selectedDate.pickDate()

        }


        cancelBtn.setOnClickListener {
            view.findNavController().navigate(R.id.action_addTaskFragment_to_mainFragment)
        }

        addBtn.setOnClickListener {
            if (title.text.toString().isNotEmpty()) {
                viewModel.insertTask(addTask())
                view.findNavController().navigate(R.id.action_addTaskFragment_to_mainFragment)
            }
        }

    }


    private fun addTag(): String? {
        return if (tag.text.toString().isNotEmpty()) {
            tag.text.toString()
        } else null
    }

    private fun addTask(): Task {
        return addTag()?.let {
            Task(
                title.text.toString(),
                getCurrentDate(),
                selectedDate.text.toString().ifEmptyThenNull(),
                false,
                description.text.toString().ifEmptyThenNull(),
                it
            )
        } ?: Task(
            title.text.toString(),
            getCurrentDate(),
            selectedDate.text.toString().ifEmptyThenNull(),
            false,
            description.text.toString().ifEmptyThenNull(),
        )

    }
    private fun findView(view: View) {
        title = view.findViewById(R.id.addTitle)
        description = view.findViewById(R.id.addDescription)
        tag = view.findViewById(R.id.addTag)
        selectedDate = view.findViewById(R.id.addDueDate)
        datepick = view.findViewById(R.id.addDatePick)
        addBtn = view.findViewById(R.id.addpageaddbtn)
        cancelBtn = view.findViewById(R.id.addPageCancelbtn)

    }

}