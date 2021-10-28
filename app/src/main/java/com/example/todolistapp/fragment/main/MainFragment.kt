package com.example.todolistapp.fragment.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.SharedViewModel
import com.example.todolistapp.update.DialogWithData
import com.example.todolistapp.update.DialogWithDataArgs
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: FloatingActionButton

    //bottom sheet
    private lateinit var bottomSheet: ConstraintLayout
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var tvTitle: TextView
    private lateinit var tvSubTitle: TextView
    private lateinit var btmSheetDelete: Button
    private lateinit var btmSheetUpdate: Button


    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.main_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
        findView(view)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        refreshView()
        //animation at the start of loading the view
        val lac =
            LayoutAnimationController(AnimationUtils.loadAnimation(view.context, R.anim.item_anim))
        lac.delay = 0.20f
        lac.order = LayoutAnimationController.ORDER_NORMAL
        recyclerView.layoutAnimation = lac


        //get selected item from recycler view
        viewModel.taskLive.observeForever(Observer { task ->

            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
            tvTitle.setText(task.title)
            tvSubTitle.text = "${task.createDescription} ${task.createDate}"

            btmSheetDelete.setOnClickListener {
                viewModel.delete(task)
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                refreshView()

            }

            btmSheetUpdate.setOnClickListener {
                val action: NavDirections =MainFragmentDirections.actionMainFragmentToDialogWithData(task)
                view.findNavController().navigate(action)
             //   DialogWithData().show(parentFragmentManager, DialogWithData.TAG)
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
               // refreshView()

            }

            //not working use new fragments,nav instead!!
           /* viewModel.name.observeForever {
                tvname.text = it
                Toast.makeText(view.context,"I SEE YOU: $it, name:${tvname.text}",Toast.LENGTH_LONG).show()
            }*/

        })


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

    fun refreshView() {
        viewModel.getAllTasks().observeForever(Observer {
            recyclerView.adapter = Adapter(it, viewModel)

        })
    }

    private fun findView(view: View) {
        recyclerView = view.findViewById(R.id.rvRecycleView)
        addButton = view.findViewById(R.id.btnAdd)

        //bottom sheet
        bottomSheet = view.findViewById(R.id.main_bottom_sheet)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        tvTitle = view.findViewById(R.id.tvTitle)
        tvSubTitle = view.findViewById(R.id.tvSubtitle)
        btmSheetDelete = view.findViewById(R.id.btmSheetDelete)
        btmSheetUpdate = view.findViewById(R.id.btmSheetUpdate)


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