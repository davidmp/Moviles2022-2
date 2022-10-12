package pe.edu.upeu.proyectovcmjc.ui.presentation.screens.persona

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.proyectovcmjc.modelo.Persona
import pe.edu.upeu.proyectovcmjc.repository.PersonaRepository
import javax.inject.Inject

@HiltViewModel
class PersonaFormViewModel @Inject constructor(
    private val personRepo: PersonaRepository,
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    fun getPersona(idX: Int):LiveData<Persona> {
        return personRepo.buscarPersonaId(idX)
    }
    val isLoading: LiveData<Boolean> get() = _isLoading
    fun addPersona(persona: Persona){
        Log.i("VMDATA", ""+persona.dni)
        viewModelScope.launch(Dispatchers.IO){
            personRepo.insertarPersona(persona)
        }
    }
}