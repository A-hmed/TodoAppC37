package com.route.todoappc37.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import com.route.todoappc37.R
import com.route.todoappc37.ui.adapters.TodosAdapter
import com.route.todoappc37.ui.database.MyDataBase
import java.util.*

//Calendar: class Built in android sdk
//Date: --------------------------
//CalendarDay: class from package (calendar view)

class ListFragment: Fragment() {
    lateinit var calendarView:MaterialCalendarView
    lateinit var recyclerView: RecyclerView
    var adapter = TodosAdapter(listOf())
     var selectedDay: Calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        initListeners()
        refreshTodos()
    }
    fun initViews(view: View){
        calendarView = view.findViewById(R.id.calendarView)
        calendarView.selectedDate = CalendarDay.today()

        recyclerView = view.findViewById(R.id.todosRecyclerView)
        recyclerView.adapter = adapter
    }
    fun initListeners(){
        calendarView.setOnDateChangedListener(object: OnDateSelectedListener{
            override fun onDateSelected(
                widget: MaterialCalendarView,
                date: CalendarDay,
                selected: Boolean
            ) {
                selectedDay.set(date.year, date.month-1, date.day)
                refreshTodos()
            }
        })
    }

   fun refreshTodos(){
       selectedDay.clear(Calendar.HOUR)
       selectedDay.clear(Calendar.MINUTE)
       selectedDay.clear(Calendar.SECOND)
       selectedDay.clear(Calendar.MILLISECOND)
       val todos = MyDataBase.getInstance(requireContext()).getTodoDao().getTodosByDate(
           selectedDay.time.time
       )
       adapter.changeData(todos)
   }
}