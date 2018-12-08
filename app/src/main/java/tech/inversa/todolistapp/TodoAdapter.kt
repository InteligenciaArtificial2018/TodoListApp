package tech.inversa.todolistapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import tech.inversa.todolistapp.data.Todo

class TodoAdapter(var todoList: List<Todo>? = ArrayList<Todo>()): RecyclerView.Adapter<TodoAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): TodoAdapter.ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.template_todo_item, parent, false)

        return ViewHolder(vista, todoList!!)
    }

    override fun getItemCount(): Int {
        return todoList?.count()!!
    }

    override fun onBindViewHolder(holder: TodoAdapter.ViewHolder, position: Int) {
        holder.onBindViews(position)
    }

    class ViewHolder(val vista: View, val todoList: List<Todo>): RecyclerView.ViewHolder(vista) {
        fun onBindViews(position: Int) {
            vista.findViewById<TextView>(R.id.tvTitulo).text = todoList.get(position).asunto
            vista.findViewById<TextView>(R.id.tvPrimeraLetra).text = todoList.get(position).asunto.first().toUpperCase().toString()
            vista.findViewById<ImageView>(R.id.ivPrioridad).setImageResource(getImage(todoList.get(position).prioridad))
        }

        private fun getImage(prioridad: Int): Int
        = if (prioridad == 1) R.drawable.low_priority else if (prioridad == 2) R.drawable.medium_priority else R.drawable.high_priority
    }
}