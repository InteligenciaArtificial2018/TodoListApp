package tech.inversa.todolistapp.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ToDoDao {
    /**
     * Retorna todos las tuplas de Todo en orden ascendente.
     */
    @Query("SELECT * FROM todo ORDER BY id ASC")
    fun getTodoList(): List<Todo>

    @Query("SELECT * FROM todo WHERE id = :id")
    fun getTodoItem(id: Int): Todo

}