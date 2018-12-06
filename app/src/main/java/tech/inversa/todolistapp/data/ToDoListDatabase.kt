package tech.inversa.todolistapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class ToDoListDatabase: RoomDatabase() {
    /**
     * Este es un método abstracto que retorna el DAO para la base de datos.
     */
    abstract fun getToDoDao(): ToDoDao

    /**
     * Un patrón de diseño Singleton es utilizado para asegurarnos que
     * solamente se cree una instancia de la base de datos.
     */
    companion object {
        val databaseName = "tododatabase"
        var todoListDatabase: ToDoListDatabase? = null

        fun getInstance(context: Context): ToDoListDatabase? {
            if (todoListDatabase == null) {
                todoListDatabase = Room.databaseBuilder(context,
                    ToDoListDatabase::class.java,
                    ToDoListDatabase.databaseName)
                    .allowMainThreadQueries()
                    .build()
            }
            return todoListDatabase
        }
    }
}