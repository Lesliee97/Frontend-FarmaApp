package com.ejemplo.login.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ejemplo.login.R
import com.ejemplo.login.io.response.LoginRequest
import com.ejemplo.login.io.response.data.RegisterRequest
import com.ejemplo.login.io.response.data.UserFindResponse
import com.ejemplo.login.service.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class RegistroCompletoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_completo)

        val user: UserFindResponse? = intent.getParcelableExtra("user_find")
        user?.let {
            // Actualiza tu UI con los datos del usuario

            findViewById<TextView>(R.id.txt_dni_registro).apply {
                text = it.dni
                isFocusable = false
                isClickable = false
                isEnabled = false
            }

            findViewById<TextView>(R.id.txt_nombres_registro).apply {
                text = it.nombres
                isFocusable = false
                isClickable = false
                isEnabled = false
            }

            findViewById<TextView>(R.id.txt_apellidos_registro).apply {
                text = it.apellidos
                isFocusable = false
                isClickable = false
                isEnabled = false
            }

            findViewById<TextView>(R.id.txt_historia_registro).apply {
                text = it.historia
                isFocusable = false
                isClickable = false
                isEnabled = false
            }

            /*
            findViewById<TextView>(R.id.txt_dni_registro).text = it.dni
            findViewById<TextView>(R.id.txt_nombres_registro).text = it.nombres
            findViewById<TextView>(R.id.txt_apellidos_registro).text = it.apellidos
            findViewById<TextView>(R.id.txt_historia_registro).text = it.historia*/
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btn_guardar = findViewById<Button>(R.id.btn_guardar)
        btn_guardar.setOnClickListener{
            goToGuardar()
        }

    }
    private fun goToMenu(){
        val i = Intent(this, MenuActivity::class.java)
        startActivity(i)
        finish()
    }

    private fun goToGuardar(){
        val txt_username = findViewById<EditText>(R.id.txt_dni_registro).text.toString()
        val txt_password = findViewById<EditText>(R.id.txt_contrasena_registro).text.toString()
        val txt_nombres = findViewById<EditText>(R.id.txt_nombres_registro).text.toString()
        val txt_apellidos = findViewById<EditText>(R.id.txt_apellidos_registro).text.toString()
        val txt_historia = findViewById<EditText>(R.id.txt_historia_registro).text.toString()
        val txt_celular = findViewById<EditText>(R.id.txt_celular_registro).text.toString()
        val txt_correo = findViewById<EditText>(R.id.txt_correo_registro).text.toString()


        //val call = apiService.postLogin(LoginRequest(txt_dni,txt_password))
        GlobalScope.launch (Dispatchers.IO) {
            try {
                val response= RetrofitClient.instance.postRegister(RegisterRequest(txt_username, txt_password, txt_nombres, txt_apellidos, txt_historia, txt_celular, txt_correo))

                withContext(Dispatchers.Main){
                    Toast.makeText(this@RegistroCompletoActivity,"token: ${response.toString()}",Toast.LENGTH_SHORT).show()
                    goToMenu()
                }
            }catch (e: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@RegistroCompletoActivity,"Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}