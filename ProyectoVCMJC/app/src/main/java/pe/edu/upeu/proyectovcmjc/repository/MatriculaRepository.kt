package pe.edu.upeu.proyectovcmjc.repository

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.edu.upeu.proyectovcmjc.data.local.dao.MatriculaDao
import pe.edu.upeu.proyectovcmjc.data.remote.RestMatricula
import pe.edu.upeu.proyectovcmjc.modelo.Matricula
import pe.edu.upeu.proyectovcmjc.modelo.User
import pe.edu.upeu.proyectovcmjc.util.TokenUtils

import javax.inject.Inject
interface MatriculaRepository {
    suspend fun insertarMatricula(matricula: Matricula):Boolean
    fun reportarMatriculas(): LiveData<List<Matricula>>
}
class MatriculaRepositoryImp @Inject constructor(
    private val dataSource: RestMatricula,
    private val matriculaDao: MatriculaDao
):MatriculaRepository{
    override suspend fun insertarMatricula(matricula: Matricula): Boolean {
        var dd:Boolean=false
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("DATA", "T:"+ TokenUtils.TOKEN_CONTENT)
            Log.i("DATA", "D:"+matricula.toString())
            dd=dataSource.insertarMatricula(TokenUtils.TOKEN_CONTENT,matricula).body()?.success!!
        }
        return dd
    }
    override fun reportarMatriculas(): LiveData<List<Matricula>> {
        //delay(3000)
        try {
            CoroutineScope(Dispatchers.IO).launch {
                delay(3000)
                val totek=dataSource.login(User("", "davidmp@upeu.edu.pe", "12345678"))
                TokenUtils.TOKEN_CONTENT=totek?.token_type+" "+totek?.access_token
                Log.i("VERXX", "Token:"+TokenUtils.TOKEN_CONTENT)
                val data=dataSource.reportarMatricula(TokenUtils.TOKEN_CONTENT).body()!!.data
                Log.i("DMP", "Data:"+data.size)
                val dm = ArrayList<Matricula>()
                data.forEach {
                    dm.add(Matricula(it.id, it.estado,it.persona.id))
                }
                Log.i("DMP", "DataR:"+dm.size)
                matriculaDao.insetarMatricula(dm)
            }
        }catch (e:Exception){
            Log.i("ERRORX", "Error:"+e.message)
        }
        return matriculaDao.reportarMatriculas()
    }
}