package pe.edu.upeu.proyectovcmjc.modelo


import androidx.room.Entity
import androidx.room.ForeignKey

import androidx.room.PrimaryKey

@Entity(tableName = "matricula",
    foreignKeys = arrayOf(
        ForeignKey(entity = Persona::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("persona"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ))
)
data class Matricula(
    @PrimaryKey(autoGenerate = true) var id:Int,
    val estado:String,
    val persona:Int,
)

data class MatriculaResp(
    var id:Int,
    val estado:String,
    val persona:Persona,
)
