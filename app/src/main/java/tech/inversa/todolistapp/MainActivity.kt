package tech.inversa.todolistapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import tech.inversa.todolistapp.data.ToDoListDatabase
import tech.inversa.todolistapp.data.Todo

class MainActivity : AppCompatActivity(), TodoAdapter.OnTodoItemClickListener {
    private var todoDatabase: ToDoListDatabase? = null
    private var todoAdapter: TodoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoDatabase = ToDoListDatabase.getInstance(this)
        todoAdapter = TodoAdapter(todoDatabase?.getToDoDao()?.getTodoList())
        todoAdapter?.setTodoItemClickListener(this)

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

    /**
     * Sobreescribimos la funcionalidad de click desde la herencia de la interfaz
     * del TodoAdapter. Enviamos la información mediante un intent con parámetros
     * hacia el layout correspondiente.
     */
    override fun onTodoItemClickListener(todo: Todo) {
        val intent = Intent(this, ToDoAddActivity::class.java)
        intent.putExtra("id", todo.id)
        intent.putExtra("asunto", todo.asunto)
        intent.putExtra("prioridad", todo.prioridad)
        intent.putExtra("detalle", todo.detalle)
        startActivity(intent)
    }

    override fun onTodoItemLongClickListener(todo: Todo) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
