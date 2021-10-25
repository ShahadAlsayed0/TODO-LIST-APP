package com.example.todolistapp.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.Button
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.database.data.Task
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var fillButton: FloatingActionButton

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        findView(view)
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        //animation at the start of loading the view
        val lac =
            LayoutAnimationController(AnimationUtils.loadAnimation(view.context, R.anim.item_anim))
        lac.delay = 0.20f
        lac.order = LayoutAnimationController.ORDER_NORMAL
        recyclerView.layoutAnimation = lac


     //   recyclerView.adapter = Adapter(dummyListOfTasks())

        // viewModel.fillDB()

        /* fillButton.setOnClickListener(){
             viewModel.getAllTasks().observe(viewLifecycleOwner, Observer {
                 recyclerView.adapter = Adapter(it)
             })
         }*/
        fillButton.setOnClickListener() {
           // recyclerView.startLayoutAnimation()

           // view.findNavController().navigate(R.id.action_mainFragment_to_testRoomData)
            view.findNavController().navigate(R.id.action_mainFragment_to_addTaskFragment)


        }

    }

    private fun findView(view: View) {
        recyclerView = view.findViewById(R.id.rvRecycleView)
        fillButton = view.findViewById(R.id.btnAdd)
    }
}
/*

fun dummyListOfTasks(): MutableList<Task> {
var countID=-1
    val task1 = Task(
        ++countID,"do my homework", "22/12/2021", null, false, "gotta add title to it", null,
        0)
        //mutableListOf("TO DO", "Home Work"))
    val task2 = Task(
        ++countID,"eat breakfast", "21/12/2021", null, false, "gotta add title to it", null,
        1)
        //mutableListOf("TO DO", "Home Work"))

    val task3 = Task(
        ++countID,"Today I bought a raincoat and wore it on a sunny day",
        "22/12/2021",
        null,
        false,
        "gotta add title to it",
        null,
        1)
        //mutableListOf("TO DO", "Home Work"))

    val task4 = Task(
        ++countID,"There's no alternative", "22/12/2021", null, false, "gotta add title to it", null,
        2)
        //mutableListOf("TO DO", "Home Work"))

    val task5 = Task(
        ++countID,"Tom left the next year", "22/12/2021", null, false, "gotta add title to it", null,
        2)
        //mutableListOf("TO DO", "Home Work"))

    val task6 = Task(
        ++countID," What's the problem with your computer?",
        "22/12/2021",
        null,
        false,
        "gotta add title to it",
        null,
        3)
        //mutableListOf("TO DO", "Home Work"))

    val task7 = Task(
        ++countID,"do my homework", "22/12/2021", null, false, "gotta add title to it", null,
        4)
        //mutableListOf("TO DO", "Home Work"))

    return mutableListOf(task1, task2, task3, task4, task5, task6, task7)
}*/
