package com.route.todoappc37.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.route.todoappc37.R
import com.route.todoappc37.ui.database.model.Todo

class TodosAdapter(var items: List<Todo>): RecyclerView.Adapter<TodosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return ViewHolder(view)
    }

    fun changeData(newList: List<Todo>){
        items = newList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = items.get(position)
        holder.titleTextView.text = todo.title
        holder.descriptionTextView.text = todo.description
    }

    override fun getItemCount(): Int {
        return items.size
    }
    class ViewHolder(val itemView: View): RecyclerView.ViewHolder(itemView){
        val titleTextView  = itemView.findViewById<TextView>(R.id.titleTextView)
        val  descriptionTextView  = itemView.findViewById<TextView>(R.id.descriptionTextView)
        val icCheck  = itemView.findViewById<ImageView>(R.id.checkIcon)
    }
}