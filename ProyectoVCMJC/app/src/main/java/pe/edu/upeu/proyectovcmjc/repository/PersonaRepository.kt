package pe.edu.upeu.proyectovcmjc.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.delay
import pe.edu.upeu.proyectovcmjc.data.local.dao.PersonaDao
import pe.edu.upeu.proyectovcmjc.data.remote.RestDataSource
import pe.edu.upeu.proyectovcmjc.modelo.Persona
import pe.edu.upeu.proyectovcmjc.util.TokenUtils
import javax.inject.Inject

interface PersonaRepository {
    suspend fun deletePersona(persona: Persona)
    fun reportarPersonas():LiveData<List<Persona>>
}

class PersonaRepositoryImp @Inject constructor(
    private val dataSource:RestDataSource,
    private val personaDao: PersonaDao
):PersonaRepository{
    override suspend fun deletePersona(persona: Persona)=personaDao.eliminarPersona(persona)

    override fun reportarPersonas(): LiveData<List<Persona>> {
        //delay(3000)
        val data=dataSource.reportarPersona(TokenUtils.TOKEN_CONTENT).body()!!.data
        personaDao.insetarPersonas(data)
        return personaDao.reportarPersonas()
    }

}