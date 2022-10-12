package pe.edu.upeu.proyectovcmjc.repository

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.edu.upeu.proyectovcmjc.data.local.dao.PersonaDao
import pe.edu.upeu.proyectovcmjc.data.remote.RestDataSource
import pe.edu.upeu.proyectovcmjc.modelo.Persona
import pe.edu.upeu.proyectovcmjc.modelo.User
import pe.edu.upeu.proyectovcmjc.util.TokenUtils
import javax.inject.Inject

interface PersonaRepository {
    suspend fun deletePersona(persona: Persona)
    fun reportarPersonas():LiveData<List<Persona>>
    fun buscarPersonaId(id:Int):LiveData<Persona>
    suspend fun insertarPersona(persona: Persona)

}

class PersonaRepositoryImp @Inject constructor(
    private val dataSource:RestDataSource,
    private val personaDao: PersonaDao
):PersonaRepository{
    override suspend fun deletePersona(persona: Persona){
        CoroutineScope(Dispatchers.IO).launch {
            dataSource.deletePersona(persona.id)
        }
        personaDao.eliminarPersona(persona)
    }

    override fun reportarPersonas(): LiveData<List<Persona>> {
        //delay(3000)
        try {
            CoroutineScope(Dispatchers.IO).launch {
                delay(3000)
                val totek=dataSource.login(User("", "davidmp@upeu.edu.pe", "d123456"))
                TokenUtils.TOKEN_CONTENT=totek?.token_type+" "+totek?.access_token
                Log.i("VERX", "Token:"+TokenUtils.TOKEN_CONTENT)
                val data=dataSource.reportarPersona(TokenUtils.TOKEN_CONTENT).body()!!.data
                personaDao.insetarPersonas(data)
            }
        }catch (e:Exception){
            Log.i("ERRORX", "Error:"+e.message)
        }
        return personaDao.reportarPersonas()
    }

    override fun buscarPersonaId(id: Int): LiveData<Persona> {
       return personaDao.buscarPersona(id)
    }

    override suspend fun insertarPersona(persona: Persona) {
        CoroutineScope(Dispatchers.IO).launch {
            dataSource.insertarPersona(persona)
        }
    }

}