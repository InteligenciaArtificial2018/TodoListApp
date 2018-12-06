package tech.inversa.todolistapp.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

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