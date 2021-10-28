package com.example.todolistapp.update

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todolistapp.*
import com.example.todolistapp.database.model.Task
import java.util.*

class DialogWithData : DialogFragment() {
    private lateinit var viewModel: SharedViewModel
    private lateinit var btnSubmit: Button
    private lateinit var title: AppCompatEditText
    private lateinit var description: AppCompatEditText
    private lateinit var datepick: ImageButton
    private lateinit var selectedDate: AppCompatTextView

    companion object {

        const val TAG = "DialogWithData"

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dialog_with_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        findView(view)

        val args: DialogWithDataArgs by navArgs()
        val originalTask = args.originalTask
        title.hint = originalTask.title
        description.hint = originalTask.createDescription
        selectedDate.hint = originalTask.dueDate

        datepick.setOnClickListener {
            selectedDate.text = view.pickDate()
        }


        btnSubmit.setOnClickListener {

            val newTitle =  title.text.toString().ifEmptyThenNull() ?: originalTask.title
            val newDescription = description.text.toString().ifEmptyThenNull() ?: originalTask.createDescription
            val newDueDate = selectedDate.text.toString().ifEmptyThenNull() ?: originalTask.dueDate

            viewModel.update(updateTask(newTitle,newDescription,newDueDate,originalTask.id,originalTask.Tag))

           // val action: NavDirections = DialogWithDataDirections.actionDialogWithDataToMainFragment()
           // view.findNavController().navigate(action)

            dismiss()
        }

    }
private fun updateTask(newTitle:String, newDescription:String?, newDueDate:String?,originalID:Int,originalTAG:String):Task{
    return Task(
        originalID,
        newTitle,
        getCurrentDate(),
        newDueDate,
        false,
        newDescription,
        null,
        originalTAG
    )
}
    private fun findView(view: View) {
        btnSubmit = view.findViewById(R.id.btnSubmit)
        title = view.findViewById(R.id.newTitle)
        description = view.findViewById(R.id.newDescription)
        selectedDate = view.findViewById(R.id.newDueDate)
        datepick = view.findViewById(R.id.newDatePick)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun setupClickListeners() {

    }

}