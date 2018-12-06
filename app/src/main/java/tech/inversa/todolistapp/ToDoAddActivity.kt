package tech.inversa.todolistapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.activity_to_do_add.*
import kotlinx.android.synthetic.main.template_todo_item.*
import tech.inversa.todolistapp.data.ToDoListDatabase
import tech.inversa.todolistapp.data.Todo

class ToDoAddActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener {

    private var todoDatabase: ToDoListDatabase? = null
    private var prioridad = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do_add)

        todoDatabase = ToDoListDatabase.getInstance(this)
        rgPrioridad.setOnCheckedChangeListener(this)

        // Validar si se nos envía el intent con el valor del título
        val titulo = intent.getStringExtra("titulo")
        val descripcion = intent.getStringExtra("descripcion")

        // Si no está definido o viene en blanco, el usuario presionó el FAB
        if (titulo == null || titulo == "") {
            btnAgregarTarea.setOnClickListener {
                val tarea = Todo(etTitulo.text.toString(), etDescripcion.text.toString(), prioridad)
                todoDatabase?.getToDoDao()?.saveTodo(tarea)
                finish()
            }
        } else {
            val id = intent.getIntExtra("id", 0)
            etTitulo.setText(titulo)
            etDescripcion.setText(descripcion)
            btnAgregarTarea.setOnClickListener {
                val tarea = Todo(etTitulo.text.toString(), etDescripcion.text.toString(), prioridad)
                tarea.id = id
                todoDatabase?.getToDoDao()?.updateTodo(tarea)
                finish()
            }
        }
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        if (checkedId == R.id.rbMedia) {
            prioridad = 2
        } else if (checkedId == R.id.rbAlta) {
            prioridad = 3
        }
    }
}
