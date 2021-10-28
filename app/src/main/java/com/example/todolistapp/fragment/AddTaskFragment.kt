package com.example.todolistapp.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import androidx.navigation.findNavController
import com.example.todolistapp.*
//import com.example.todolistapp.database.data.Tag
import com.example.todolistapp.database.model.Task

class AddTaskFragment : Fragment() {
    private lateinit var title: androidx.appcompat.widget.AppCompatEditText
    private lateinit var description: androidx.appcompat.widget.AppCompatEditText
    private lateinit var tag: androidx.appcompat.widget.AppCompatEditText
    private lateinit var datepick: ImageButton
    private lateinit var selectedDate: androidx.appcompat.widget.AppCompatTextView
    private lateinit var addTask: Button
    private lateinit var cancel: Button


    private lateinit var viewModel: SharedViewModel


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
        viewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        findView(view)
        datepick.setOnClickListener {
            selectedDate.text = view.pickDate()
        }
        cancel.setOnClickListener {
            view.findNavController().navigate(R.id.action_addTaskFragment_to_mainFragment)
            // view.findNavController().navigate(R.id.action_addTaskFragment_to_testRoomData2)

        }

        addTask.setOnClickListener {
            if (title.text.toString().isNotEmpty()) {
                /*       // ${LocalDateTime.now( ZoneId.of(ZoneId.systemDefault().id))}
                       // ${LocalDateTime.now(TimeZone.getDefault().toZoneId())}
                        Toast.makeText(
                            view.context,
                            " result : ${LocalDateTime.now(TimeZone.getDefault().toZoneId())} ",
                            Toast.LENGTH_LONG
                        ).show()
                       //   viewModel.insertTask(addTask())
                       viewModel.insertTask(addTask())

                       addTag()?.let { tag ->
                           viewModel.insertTag(tag)
                       }

                       if (newTagCheck.isChecked) {
                          // viewModel.insertTaskToTag(TAGID, TASKID)
                       }
                      if(toDoCheck.isChecked) {
                         // viewModel.insertTaskToTag(TODOTAG.id, TASKID)
                      }
               */

                /* addTag()?.let { tag ->
                         viewModel.insertTaskANDTag(addTask(),tag)
                     }*/


                viewModel.insertTask(addTask())

                view.findNavController().navigate(R.id.action_addTaskFragment_to_mainFragment)
                //  view.findNavController().navigate(R.id.action_addTaskFragment_to_testRoomData2)

            } else {
                Toast.makeText(view.context, "Must Enter a Title", Toast.LENGTH_SHORT).show()
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
                null,
                it
            )
        } ?: Task(
            title.text.toString(),
            getCurrentDate(),
            selectedDate.text.toString().ifEmptyThenNull(),
            false,
            description.text.toString().ifEmptyThenNull(),
            null,
        )

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