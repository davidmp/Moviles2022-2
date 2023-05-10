package pe.edu.upeu.proyectovcmjc.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import pe.edu.upeu.proyectovcmjc.data.local.dao.MatriculaDao
import pe.edu.upeu.proyectovcmjc.data.local.dao.PersonaDao
import pe.edu.upeu.proyectovcmjc.modelo.Matricula
import pe.edu.upeu.proyectovcmjc.modelo.Persona
import pe.edu.upeu.proyectovcmjc.modelo.User

@Database(entities = [Persona::class, Matricula::class], version = 1)
abstract class DbDataSource:RoomDatabase() {
    abstract fun personaDao():PersonaDao

    abstract fun matriculaDao():MatriculaDao
}