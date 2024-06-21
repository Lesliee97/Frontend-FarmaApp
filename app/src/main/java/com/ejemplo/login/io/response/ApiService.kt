package com.ejemplo.login.io.response

import com.ejemplo.login.io.response.data.RegisterRequest
import com.ejemplo.login.io.response.data.RegisterResponse
import com.ejemplo.login.io.response.data.UserFindResponse
import com.ejemplo.login.io.response.data_medicamento.MedicamentosRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST(value="auth/login")
    suspend fun postLogin(@Body loginRequest: LoginRequest):
            LoginResponse

    @POST(value="auth/register")
    suspend fun postRegister(@Body registerRequest: RegisterRequest):
            RegisterResponse

    @GET(value="usuarios/usuarioByDni/{dni}")
    suspend fun getUserByDni(@Path("dni") dni: String):
            UserFindResponse

    @GET(value="medicamentos/getAllMedicamentos")
    suspend fun getAllMedicamentos(): List<MedicamentosRequest>


}