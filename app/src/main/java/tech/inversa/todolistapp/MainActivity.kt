package tech.inversa.todolistapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import tech.inversa.todolistapp.data.ToDoListDatabase

class MainActivity : AppCompatActivity() {
    private var todoDatabase: ToDoListDatabase? = null
    private var todoAdapter: TodoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoDatabase = ToDoListDatabase.getInstance(this)
        todoAdapter = TodoAdapter(todoDatabase?.getToDoDao()?.getTodoList())

        // Llamar la activity de agregar tarea mediante el floating action button
        fabAddToDo.setOnClickListener {
            startActivity(Intent(this, ToDoAddActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        todoAdapter?.todoList = todoDatabase?.getToDoDao()?.getTodoList()
        rvToDo.adapter = todoAdapter
        rvToDo.layoutManager = LinearLayoutManager(this)
        rvToDo.hasFixedSize()
    }
}
