package com.example.todolistapp.ui.main

import android.animation.ArgbEvaluator
import android.content.Context
import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.SharedViewModel
import com.example.todolistapp.getCurrentDate
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
    private lateinit var creationdate: TextView
    private lateinit var state: TextView
    private lateinit var tag: TextView
    private lateinit var bottom_text: TextView
    private lateinit var btmSheetDelete: Button
    private lateinit var btmSheetUpdate: Button

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

        //animation at the start of loading the view
        val lac =
            LayoutAnimationController(AnimationUtils.loadAnimation(view.context, R.anim.item_anim))
        lac.delay = 0.20f
        lac.order = LayoutAnimationController.ORDER_NORMAL
        recyclerView.layoutAnimation = lac
        //recyclerView.startLayoutAnimation()
        refreshView(view.context)

        //get selected item from recycler view
        viewModel.taskLive.observeForever(Observer { task ->

            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
            tvTitle.setText(task.title)
            tvSubTitle.text = task.createDescription
            creationdate.text = task.createDate


            tag.text = task.Tag
            btmSheetDelete.setOnClickListener {
                viewModel.delete(task)
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                refreshView(view.context)

            }
            //working but not very good
            if (!task.dueDate.isNullOrEmpty()) {
                if (task.dueDate < getCurrentDate()) {
                    btmSheetUpdate.isEnabled = false
                    state.text = "Overdue"
                    state.setTextColor(resources.getColor(R.color.red))
                    bottom_text.text = "Overdue"
                    bottom_text.setTextColor(resources.getColor(R.color.red))

                } else {
                    btmSheetUpdate.isEnabled = true
                    state.text = ""
                    bottom_text.text = ""
                }
            }
            ///////
            btmSheetUpdate.setOnClickListener {
                val action: NavDirections = MainFragmentDirections.actionMainFragmentToDialogWithData(task)
                view.findNavController().navigate(action)
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN


            }
            bottomSheetBehavior.addBottomSheetCallback(object :
                BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {

                    if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                        view.setBackgroundColor(ContextCompat.getColor(view.context, R.color.white))
                        view.background = resources.getDrawable(R.drawable.background)
                        refreshView(view.context)
                        addButton.visibility = VISIBLE

                    }
                    if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                        view.setBackgroundColor(ContextCompat.getColor(view.context, R.color.white))
                        view.background = resources.getDrawable(R.drawable.background)
                        refreshView(view.context)
                        addButton.visibility = VISIBLE

                      //  recyclerView.startLayoutAnimation()

                    }
                    if (newState == BottomSheetBehavior.STATE_EXPANDED||newState == BottomSheetBehavior.STATE_HALF_EXPANDED) {
                        addButton.visibility = GONE
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    transitionBottomSheetParentView(slideOffset, view)
                }
            })
        })

        /* val cDateTextView: TextView = itemView.findViewById(R.id.item_creation_date)
                val completedTextView: TextView = itemView.findViewById(R.id.item_completed)
                 val cDescriptionTextView: TextView = itemView.findViewById(R.id.item_Description_on_create)
                 val oDDescriptionTextView: TextView = itemView.findViewById(R.id.item_Description_over_due)
        */


        addButton.setOnClickListener() {
            view.findNavController().navigate(R.id.action_mainFragment_to_addTaskFragment)
        }
    }

    private fun transitionBottomSheetParentView(slideOffset: Float, view: View) {
        if (slideOffset > 0) {
            val argbEvaluator = ArgbEvaluator().evaluate(slideOffset, 0x8189b3, Color.GRAY)
            view.setBackgroundColor(argbEvaluator as Int)
        } else {
            val argbEvaluator = ArgbEvaluator().evaluate(slideOffset, Color.GRAY, 0x8189b3)
            view.setBackgroundColor(argbEvaluator as Int)
        }

    }

    fun refreshView(context: Context) {

        viewModel.getAllTasks().observeForever(Observer {
            recyclerView.adapter = Adapter(it, viewModel, context)
            recyclerView.startLayoutAnimation()
        })
    }

    private fun findView(view: View) {
        recyclerView = view.findViewById(R.id.rvRecycleView)
        addButton = view.findViewById(R.id.btnAdd)

        //bottom sheet
        bottomSheet = view.findViewById(R.id.main_bottom_sheet)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        tvTitle = view.findViewById(R.id.detailTitle)
        tvSubTitle = view.findViewById(R.id.detailSubtitle)
        btmSheetDelete = view.findViewById(R.id.btmSheetDelete)
        btmSheetUpdate = view.findViewById(R.id.btmSheetUpdate)
        creationdate = view.findViewById(R.id.detailcreationdate)
        state = view.findViewById(R.id.detailcompleted)
        tag = view.findViewById(R.id.detailtags)
        bottom_text = view.findViewById(R.id.bottom_text)
    }
}
