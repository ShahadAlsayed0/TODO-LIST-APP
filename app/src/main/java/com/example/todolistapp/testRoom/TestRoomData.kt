package com.example.todolistapp.testRoom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.todolistapp.R
import com.example.todolistapp.database.data.Task

class TestRoomData : Fragment() {
    private lateinit var viewModel: TestRoomDataViewModel
private lateinit var testData:TextView
private lateinit var testDataTag:TextView
private lateinit var testDataTask:TextView
private lateinit var testAdd:Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.test_room_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         viewModel = ViewModelProvider(this).get(TestRoomDataViewModel::class.java)
        testData=view.findViewById(R.id.testData)
        testDataTag=view.findViewById(R.id.testDataTag)
        testDataTask=view.findViewById(R.id.testDataTask)
        testAdd=view.findViewById(R.id.testAdd)

        var str=""
        viewModel.insertTask()
        viewModel.insertTag()



        viewModel.getAllTasks().observe(viewLifecycleOwner, Observer {
            it.forEach {
                str += "${it.id} and ${it.title}\n"
            }
            testDataTask.text= str
            str=""
        })
        viewModel.getAllTags().observe(viewLifecycleOwner, Observer {
            it.forEach {
                str += "${it.id} and ${it.name}\n"
            }
            testDataTag.text= str
            str=""
        })

        viewModel.getAll().observe(viewLifecycleOwner, Observer {
            it.forEach {
                str += "${it.tagId} and ${it.taskId}\n"
            }
            testData.text= str
            str=""
        })
        testAdd.setOnClickListener {
            view.findNavController().navigate(R.id.action_testRoomData2_to_addTaskFragment)

        }
        testData.text= str
    }
    fun dummyListOfTasks(): MutableList<Task> {
        var countID=-1
        val task1 = Task(
            ++countID,"do my homework", "22/12/2021", null, false, "gotta add title to it", null,
        )
        //mutableListOf("TO DO", "Home Work"))
        val task2 = Task(
            ++countID,"eat breakfast", "21/12/2021", null, false, "gotta add title to it", null,
            )
        //mutableListOf("TO DO", "Home Work"))

        val task3 = Task(
            ++countID,"Today I bought a raincoat and wore it on a sunny day",
            "22/12/2021",
            null,
            false,
            "gotta add title to it",
            null,
            )
        //mutableListOf("TO DO", "Home Work"))

        val task4 = Task(
            ++countID,"There's no alternative", "22/12/2021", null, false, "gotta add title to it", null,
            )
        //mutableListOf("TO DO", "Home Work"))

        val task5 = Task(
            ++countID,"Tom left the next year", "22/12/2021", null, false, "gotta add title to it", null,
            )
        //mutableListOf("TO DO", "Home Work"))

        val task6 = Task(
            ++countID," What's the problem with your computer?",
            "22/12/2021",
            null,
            false,
            "gotta add title to it",
            null,
            )
        //mutableListOf("TO DO", "Home Work"))

        val task7 = Task(
            ++countID,"do my homework", "22/12/2021", null, false, "gotta add title to it", null,
            )
        //mutableListOf("TO DO", "Home Work"))

        return mutableListOf(task1, task2, task3, task4, task5, task6, task7)
    }
}