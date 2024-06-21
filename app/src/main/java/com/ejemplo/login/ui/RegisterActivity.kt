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
import com.ejemplo.login.io.response.data.UserFindResponse
import com.example.app.RetrofitClientRegister
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val text_login = findViewById<TextView>(R.id.text_login)
        text_login.setOnClickListener{
            goToRegister()
        }

        val btn_inicio = findViewById<TextView>(R.id.btn_inicio)
        btn_inicio.setOnClickListener{
            goToRegister()
        }
        val btn_consultar = findViewById<Button>(R.id.btn_consultar)
        btn_consultar.setOnClickListener{
            performConsultar()
        }
    }
    private fun goToRegister(){
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

    private fun goToRegistroComopleto(user_find: UserFindResponse){
        val i = Intent(this, RegistroCompletoActivity::class.java)
        i.putExtra("user_find", user_find)
        startActivity(i)
    }
    private fun goToRechazo(){
        val i = Intent(this, RegistroRechazoActivity::class.java)
        startActivity(i)
    }

    private fun performConsultar(){
        val txt_dni = findViewById<EditText>(R.id.txt_dni_consulta).text.toString()
        GlobalScope.launch (Dispatchers.IO) {
            try {
                val response= RetrofitClientRegister.instance.getUserByDni(txt_dni)
                withContext(Dispatchers.Main){
                    if (response != null){
                        Toast.makeText(this@RegisterActivity,"Usuario: " + response.nombres , Toast.LENGTH_SHORT).show()
                        goToRegistroComopleto(response)
                    }else {
                        Toast.makeText(this@RegisterActivity,"Error: El usuario no está registrado en la clinica.", Toast.LENGTH_SHORT).show()
                    }
                }
            }catch (e: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@RegisterActivity,"Error: El usuario no está registrado en la clinica. ", Toast.LENGTH_SHORT).show()
                    goToRechazo()
                }
            }
        }
    }
}