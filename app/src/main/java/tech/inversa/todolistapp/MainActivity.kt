package tech.inversa.todolistapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Llamar la activity de agregar tarea mediante el floating action button
        fabAddToDo.setOnClickListener {
            startActivity(Intent(this, ToDoAddActivity::class.java))
        }
    }
}
