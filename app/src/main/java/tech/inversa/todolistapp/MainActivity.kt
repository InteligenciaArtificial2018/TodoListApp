package tech.inversa.todolistapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.Toast
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
        // Inicializar una nueva instancia de AlertDialog
        val builder = AlertDialog.Builder(this)

        // Colocar el titulo del dialogo
        builder.setTitle(R.string.tituloDialogoLongClick)

        // Mensaje a desplegar en el dialogo
        builder.setMessage(R.string.mensajeDialogoLongClick)

        // Los dialogos pueden tener hasta 3 botones, uno positivo (SI), uno negativo (NO)
        // y un boton neutro (CANCEL) los cuales utilizaremos para Modificar, Eliminar y Cancelar
        builder.setPositiveButton(R.string.modificar) {dialog, wich ->
            // Realizar el llamado a la activity de agregar enviando los valores mediante el intent
            val intent = Intent(this, ToDoAddActivity::class.java)
            intent.putExtra("id", todo.id)
            intent.putExtra("asunto", todo.asunto)
            intent.putExtra("prioridad", todo.prioridad)
            intent.putExtra("detalle", todo.detalle)
            startActivity(intent)
        }

        builder.setNegativeButton(R.string.eliminar) {dialog, which ->
            todoDatabase?.getToDoDao()?.deleteTodo(todo)
            onResume()
            Toast.makeText(this, R.string.mensajetareaEliminada, Toast.LENGTH_SHORT).show()
        }

        builder.setNeutralButton(R.string.cancelar) {dialog, which ->
            Toast.makeText(this,R.string.mensajeCancelarDialogoLongClick, Toast.LENGTH_SHORT).show()
        }

        // Crear el dialogo de alerta con todos los parámetros establecidos
        val dialogo: AlertDialog = builder.create()

        // Mostrar el dialogo
        dialogo.show()
    }
}
