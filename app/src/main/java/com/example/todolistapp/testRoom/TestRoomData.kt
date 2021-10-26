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
//import com.example.todolistapp.database.data.Tag
import com.example.todolistapp.database.data.Task

class TestRoomData : Fragment() {
    private lateinit var viewModel: TestRoomDataViewModel
    private lateinit var testData: TextView
    private lateinit var testDataTag: TextView
    private lateinit var testDataTask: TextView
    private lateinit var testjointask: TextView
    private lateinit var testjointag: TextView
    private lateinit var testAdd: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.test_room_data, container, false)
    }

   /* override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(TestRoomDataViewModel::class.java)
        testData = view.findViewById(R.id.testData)
        testDataTag = view.findViewById(R.id.testDataTag)
        testDataTask = view.findViewById(R.id.testDataTask)
        testjointask = view.findViewById(R.id.testjointask)
        testjointag = view.findViewById(R.id.testjointag)
        testAdd = view.findViewById(R.id.testAdd)

        var str = ""
*/
/*

   val tag2= Tag(2,"Home Work")
         viewModel.insertTag(tag2)
         val tag3= Tag(1,"Something")
         viewModel.insertTag(tag3)

         val task1 = Task(
             0,"do my homework", "22/12/2021", null, false, "gotta add title to it", null,)
         viewModel.insertTask(task1)
         val task2 = Task(
             1,"eat breakfast", "21/12/2021", null, false, "gotta add title to it", null,)
         viewModel.insertTask(task2)

         val task3 = Task(
             "auto generate id?", "22/12/2021", null, false, "gotta add title to it", null,)
         viewModel.insertTask(task3)
         val task4 = Task(
             "second auto generate id?", "22/12/2021", null, false, "gotta add title to it", null,)
         viewModel.insertTask(task4)


         viewModel.insertTaskToTag(-1, 0)
         viewModel.insertTaskToTag(2, 0)
         viewModel.insertTaskToTag(-1, 1)


        //  viewModel.insertTask()
        //  viewModel.insertTag()
        // viewModel.insertTaskToTag(-1, 0)
*//*

        testAdd.setOnClickListener {
            view.findNavController().navigate(R.id.action_testRoomData2_to_addTaskFragment)
        }

        viewModel.getAll().observe(viewLifecycleOwner, Observer {
            it.forEach {
                str += "TASK to TAG ${it.tagId} : ${it.taskId}\n"
            }
            testData.text = str
            str = ""
        })

          viewModel.getAllTasks().observe(viewLifecycleOwner, Observer {
            it.forEach {
                str += "TASK: ${it.id}: ${it.title}\n"
            }
            testDataTask.text = str
            str = ""
        })
        viewModel.getAllTags().observe(viewLifecycleOwner, Observer {
            it.forEach {
                str += "TAG: ${it.id}: ${it.name}\n"
            }
            testDataTag.text = str
            str = ""
        })

*//*



        viewModel.selectJoinByTaskID(0).observe(viewLifecycleOwner, Observer {
            it.forEach {
                str += "${it.taskId} and ${it.tagId}: "


viewModel.selectTagByID(it.tagId).observe(viewLifecycleOwner, Observer{
                    str +=  "${it.name} \n"
                })

            }
            testjointask.text = str
            str = ""
        })
        viewModel.selectJoinByTagID(-1).observe(viewLifecycleOwner, Observer {
            it.forEach {
                str += "${it.tagId} and ${it.taskId}: ${viewModel.selectTaskByID(it.tagId).value?.title}    \n"
            }
            testjointag.text = str
            str = ""
        })


        //get all tasks under specific tag
        viewModel.selectNEW(2).observe(viewLifecycleOwner, Observer {
            it.forEach {
                str += "${it.title}\n"

            }
            testDataTask.text = str
        })
*//*

    }*/

    /*fun dummyListOfTasks(): MutableList<Task> {
        var countID = -1
        val task1 = Task(
            ++countID, "do my homework", "22/12/2021", null, false, "gotta add title to it", null,
        )
        //mutableListOf("TO DO", "Home Work"))
        val task2 = Task(
            ++countID, "eat breakfast", "21/12/2021", null, false, "gotta add title to it", null,
        )
        //mutableListOf("TO DO", "Home Work"))

        val task3 = Task(
            ++countID, "Today I bought a raincoat and wore it on a sunny day",
            "22/12/2021",
            null,
            false,
            "gotta add title to it",
            null,
        )
        //mutableListOf("TO DO", "Home Work"))

        val task4 = Task(
            ++countID,
            "There's no alternative",
            "22/12/2021",
            null,
            false,
            "gotta add title to it",
            null,
        )
        //mutableListOf("TO DO", "Home Work"))

        val task5 = Task(
            ++countID,
            "Tom left the next year",
            "22/12/2021",
            null,
            false,
            "gotta add title to it",
            null,
        )
        //mutableListOf("TO DO", "Home Work"))

        val task6 = Task(
            ++countID, " What's the problem with your computer?",
            "22/12/2021",
            null,
            false,
            "gotta add title to it",
            null,
        )
        //mutableListOf("TO DO", "Home Work"))

        val task7 = Task(
            ++countID, "do my homework", "22/12/2021", null, false, "gotta add title to it", null,
        )
        //mutableListOf("TO DO", "Home Work"))

        return mutableListOf(task1, task2, task3, task4, task5, task6, task7)
    }*/
}