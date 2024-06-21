package com.ejemplo.login.io.response.data_medicamento

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class InicioViewModel : ViewModel() {

    private val _medicamentos = MutableLiveData<List<MedicamentosResponse>>()
    val medicamentos: LiveData<List<MedicamentosResponse>> get() = _medicamentos

    fun fetchMedicamentos() {
        viewModelScope.launch {
            try {
                //val response = RetrofitClient.instance.getAllMedicamentos()
                //_medicamentos.postValue(response)
            } catch (e: Exception) {
                // Manejar el error
            }
        }
    }
}
