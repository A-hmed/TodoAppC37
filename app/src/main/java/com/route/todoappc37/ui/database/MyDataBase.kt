package com.route.todoappc37.ui.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.route.todoappc37.ui.database.daos.TodoDao
import com.route.todoappc37.ui.database.model.Todo

@Database(entities = [Todo::class], version = 1)
abstract class MyDataBase: RoomDatabase() {
    abstract fun getTodoDao(): TodoDao

    companion object{
        private var database: MyDataBase? = null

       fun getInstance(context: Context): MyDataBase{
           if(database == null){
                database = Room.databaseBuilder(context, MyDataBase::class.java, "my database"
               ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
           }
           return database!!
       }
    }

}