package com.example.todolistapp.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.database.data.Task
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: FloatingActionButton

    //bottom sheet
    private lateinit var bottomSheet: ConstraintLayout
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var btnPersistentBottomSheet: Button
    private lateinit var tvTitle: TextView
    private lateinit var tvSubTitle: TextView

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

        //bottom sheet
        btnPersistentBottomSheet.setOnClickListener {
           // Toast.makeText(view.context, "bottom sheet visible", Toast.LENGTH_LONG).show()
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED

        }


      viewModel.taskLive.observeForever(Observer {
          Toast.makeText(view.context,it.title,Toast.LENGTH_LONG).show()
      })


        viewModel.getAllTasks().observeForever(Observer {
            recyclerView.adapter = Adapter(it,viewModel)
        })
           /* Adapter(it).onItemClick = {item ->
                Toast.makeText(view.context,item.title,Toast.LENGTH_LONG).show()

                //do something
            }*/
                // recyclerView.startLayoutAnimation()




        // viewModel.fillDB()

        /* fillButton.setOnClickListener(){
             viewModel.getAllTasks().observe(viewLifecycleOwner, Observer {
                 recyclerView.adapter = Adapter(it)
             })
         }*/
        addButton.setOnClickListener() {


            view.findNavController().navigate(R.id.action_mainFragment_to_addTaskFragment)


        }


    }

    private fun findView(view: View) {
        recyclerView = view.findViewById(R.id.rvRecycleView)
        addButton = view.findViewById(R.id.btnAdd)

        //bottom sheet
        bottomSheet = view.findViewById(R.id.main_bottom_sheet)
        btnPersistentBottomSheet = view.findViewById(R.id.btnPersistentBottomSheet)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        tvTitle=view.findViewById(R.id.tvTitle)
      //  tvTitle=view.findViewById(R.id.tvSubitle)
    }
}

/*    val task1 = Task(
          "do my homework",
          "22/12/2021",
          "1/12/2010",
          false,
          "gotta add title to it",
          null,
          "Home Work"
      )
      viewModel.insertTask(task1)
      val task2 = Task(
          "eat breakfast", "21/12/2021", "22/12/2021", false, "gotta add title to it", null,
      )
      viewModel.insertTask(task2)

      val task3 = Task(
          "Cry a little", "22/12/2021", "5/12/2010", false, "gotta add title to it", null,"Sad Times"
      )
      viewModel.insertTask(task3)
      val task4 = Task(
          "do something", "22/12/2021", null, false, "gotta add title to it", null,
      )
      viewModel.insertTask(task4)
      val task5 = Task(
          " Math homework",
          "23/12/2021",
          "2/12/2010",
          false,
          "have to do it",
          null,
          "Home Work"
      )
      viewModel.insertTask(task5)
*/