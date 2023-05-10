package pe.edu.upeu.proyectovcmjc.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pe.edu.upeu.proyectovcmjc.modelo.Matricula


@Dao
interface MatriculaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarMatricula(matricula: Matricula)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insetarMatricula(matricula: List<Matricula>)

    @Query("select * from matricula")
    fun reportarMatriculas(): LiveData<List<Matricula>>
}