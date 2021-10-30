package com.example.todolistapp.ui.main

import android.animation.ArgbEvaluator
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
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
import com.example.todolistapp.database.model.Task
import com.example.todolistapp.getCurrentDate
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.content.DialogInterface


class MainFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: FloatingActionButton

    //bottom sheet
    private lateinit var bottomSheet: ConstraintLayout
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var tvTitle: TextView
    private lateinit var tvSubTitle: TextView
    private lateinit var creationdate: TextView
    private lateinit var tag: TextView
    private lateinit var bottom_text: TextView
    private lateinit var sort: TextView
    private lateinit var deleteBtn_BS: Button
    private lateinit var updateBtn_BS: Button
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
        setAnimation(view)
        refreshView(view.context)

        addButton.setOnClickListener() {
            view.findNavController().navigate(R.id.action_mainFragment_to_addTaskFragment)
        }

        sort.setOnClickListener {
            sortDialog(view)

        }

        //get selected item from recycler view
        viewModel.taskLive.observeForever(Observer { task ->


            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
            tvTitle.setText(task.title)
            tvSubTitle.text = task.createDescription
            creationdate.text = task.createDate
            tag.text = task.Tag

            checkOverDue(task)
            deleteBtn_BS.setOnClickListener {
                viewModel.delete(task)
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                refreshView(view.context)
            }

            updateBtn_BS.setOnClickListener {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                val action: NavDirections =
                    MainFragmentDirections.actionMainFragmentToDialogWithData(task)
                view.findNavController().navigate(action)

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

                    }
                    if (newState == BottomSheetBehavior.STATE_EXPANDED || newState == BottomSheetBehavior.STATE_HALF_EXPANDED) {
                        addButton.visibility = GONE
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    transitionBottomSheetParentView(slideOffset, view)
                }
            })
        })


    }

    private fun sortDialog(view: View) {
        sort.setOnClickListener {
            val sortList = resources.getStringArray(R.array.Sorting)
            val materialAlertDialogBuilder = AlertDialog.Builder(view.context)
            materialAlertDialogBuilder.setTitle("Sort")

            materialAlertDialogBuilder.setItems(sortList) { dialog, item ->
                sort.text = sortList[item]
                refreshSorted(view, sortList[item])
                dialog.dismiss()
            }
            /* materialAlertDialogBuilder.setNeutralButton(R.string.cancel) { dialog, _ ->
                 dialog.cancel()
             }*/
            materialAlertDialogBuilder.show()
        }
    }

    private fun refreshSorted(view: View, order: String) {
        when (order) {
            "A-Z" -> {
                viewModel.sortTasksASC().observe(viewLifecycleOwner, {
                    recyclerView.adapter = Adapter(it, viewModel, view.context)
                    recyclerView.startLayoutAnimation()
                })
            }

            "Z-A" -> {
                viewModel.sortTasksDESC().observe(viewLifecycleOwner, {
                    recyclerView.adapter = Adapter(it, viewModel, view.context)
                    recyclerView.startLayoutAnimation()
                })

            }
            "creation date" -> {
                viewModel.sortTasksCreationDate().observe(viewLifecycleOwner, {
                    recyclerView.adapter = Adapter(it, viewModel, view.context)
                    recyclerView.startLayoutAnimation()
                })

            }
            "Due Date" -> {
                viewModel.sortTasksDueDate().observe(viewLifecycleOwner, {
                    recyclerView.adapter = Adapter(it, viewModel, view.context)
                    recyclerView.startLayoutAnimation()
                })

            }

        }
    }

    private fun checkOverDue(task: Task) {
        if (!task.dueDate.isNullOrEmpty()) {
            if (task.dueDate < getCurrentDate() && !task.completed) {
                updateBtn_BS.isEnabled = false
                bottom_text.text = "Overdue"
                bottom_text.setTextColor(resources.getColor(R.color.red))

            } else {
                updateBtn_BS.isEnabled = true
                bottom_text.text = ""
            }
        }
    }

    private fun setAnimation(view: View) {
        //animation at the start of loading the view
        val lac =
            LayoutAnimationController(AnimationUtils.loadAnimation(view.context, R.anim.item_anim))
        lac.delay = 0.20f
        lac.order = LayoutAnimationController.ORDER_NORMAL
        recyclerView.layoutAnimation = lac
        //recyclerView.startLayoutAnimation()
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
        deleteBtn_BS = view.findViewById(R.id.btmSheetDelete)
        updateBtn_BS = view.findViewById(R.id.btmSheetUpdate)
        creationdate = view.findViewById(R.id.detailcreationdate)
        tag = view.findViewById(R.id.detailtags)
        bottom_text = view.findViewById(R.id.bottom_text)
        sort = view.findViewById(R.id.sort)

    }
}
