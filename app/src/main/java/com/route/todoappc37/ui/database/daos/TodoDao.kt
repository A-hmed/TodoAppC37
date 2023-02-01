package com.route.todoappc37.ui.database.daos

import androidx.room.*
import com.route.todoappc37.ui.database.model.Todo

@Dao
interface TodoDao {
    @Insert
    fun addTodo(todo: Todo)

    @Delete
    fun deleteTodo(todo: Todo)

    @Update
    fun updateTodo(todo: Todo)

    @Query("select * from Todo")
    fun getTodos():List<Todo>

    @Query("select * from Todo where date = :date ")
    fun getTodosByDate(date: Long):List<Todo>

}