package tech.inversa.todolistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
class Todo(
    @ColumnInfo(name = "asunto")
    var asunto: String = "",
    @ColumnInfo(name = "detalle")
    var detalle: String = "",
    @ColumnInfo(name = "prioridad")
    var prioridad: Int = 0) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}