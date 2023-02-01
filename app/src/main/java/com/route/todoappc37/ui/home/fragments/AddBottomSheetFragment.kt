package com.route.todoappc37.ui.home.fragments

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout
import com.route.todoappc37.R
import com.route.todoappc37.ui.database.MyDataBase
import com.route.todoappc37.ui.database.model.Todo
import java.util.*

class AddBottomSheetFragment: BottomSheetDialogFragment() {
    lateinit var selectDateTv:TextView
    var selectedDate: Calendar = Calendar.getInstance()
    lateinit var titleTextInput: TextInputLayout
    lateinit  var descriptionTextInput: TextInputLayout
    lateinit var addTodoButton: Button
    var onAddClicked: OnAddClicked? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_todo, container,false)
    }
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        initListeners()
    }
    fun initViews(view: View){
        selectDateTv = view.findViewById(R.id.selectedDateTv)
        titleTextInput = view.findViewById(R.id.titleTextInputLayout)
        descriptionTextInput = view.findViewById(R.id.descriptionTextInput)
        addTodoButton = view.findViewById(R.id.addTodoButton)
        updateDateTextView()
    }
    fun initListeners(){
        selectDateTv.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),
                { p0, year, month, day ->
                    selectedDate.set(year, month, day)
                    updateDateTextView()
                }, selectedDate.get(Calendar.YEAR),
                selectedDate.get(Calendar.MONTH),
                selectedDate.get(Calendar.DAY_OF_MONTH))

            dialog.show()
        }

        addTodoButton.setOnClickListener {
              if(! validate()) return@setOnClickListener
            selectedDate.clear(Calendar.HOUR)
            selectedDate.clear(Calendar.MINUTE)
            selectedDate.clear(Calendar.SECOND)
            selectedDate.clear(Calendar.MILLISECOND)

            val todo = Todo(title = titleTextInput.editText!!.text.toString(),
            description = descriptionTextInput.editText!!.text.toString(),
             isDone = false,  date = selectedDate.time.time)
             MyDataBase.getInstance(requireContext()).getTodoDao().addTodo(todo)
             onAddClicked?.onClick()
             dismiss()
        }

    }

    interface OnAddClicked{
        fun onClick()
    }
    fun validate(): Boolean{
        var isValid = true
        if(titleTextInput.editText!!.text.isEmpty()){
            titleTextInput.error = "Please write todo title"
            isValid = false
        }else {
            titleTextInput.error = null
        }

        if(descriptionTextInput.editText!!.text.isEmpty()){
            descriptionTextInput.error = "Please write todo description"
            isValid = false
        }else {
          descriptionTextInput.error = null
        }
        return isValid
    }
    fun updateDateTextView(){
        selectDateTv.text = "${selectedDate.get(Calendar.DAY_OF_MONTH)}/ ${selectedDate.get(Calendar.MONTH)+1} / ${selectedDate.get(Calendar.YEAR)}"
    }
}