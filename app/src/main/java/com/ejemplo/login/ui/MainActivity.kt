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
import com.ejemplo.login.io.response.ApiService
import com.ejemplo.login.io.response.LoginRequest
import com.ejemplo.login.io.response.LoginResponse
import com.ejemplo.login.service.RetrofitClient
import com.ejemplo.login.util.PreferenceHelper
import com.ejemplo.login.util.PreferenceHelper.get
import com.ejemplo.login.util.PreferenceHelper.set
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val preferences = PreferenceHelper.defaultPrefs(this)
        if(preferences["token", ""].contains("."))
            goToMenu()

        val text_register = findViewById<TextView>(R.id.text_register)
        text_register.setOnClickListener{
            goToRegister()
        }

        val btn_registro = findViewById<TextView>(R.id.btn_registro)
        btn_registro.setOnClickListener{
            goToRegister()
        }

        val btn_login = findViewById<Button>(R.id.btn_login)
        btn_login.setOnClickListener{
            performLogin()
        }
    }
    private fun goToRegister(){
        val i = Intent(this, RegisterActivity::class.java)
        startActivity(i)
    }
    private fun goToMenu(){
        val i = Intent(this, MenuActivity::class.java)
        startActivity(i)
        finish()
    }

    private fun createSesionPreference(token:String){
        val preferences = PreferenceHelper.defaultPrefs(this)

        preferences["token"] = token
    }

    private fun performLogin(){
        val txt_dni = findViewById<EditText>(R.id.txt_dni).text.toString()
        val txt_password = findViewById<EditText>(R.id.txt_password).text.toString()
        //val call = apiService.postLogin(LoginRequest(txt_dni,txt_password))
        GlobalScope.launch (Dispatchers.IO) {
            try {
                val response=RetrofitClient.instance.postLogin(LoginRequest(txt_dni,txt_password))
                withContext(Dispatchers.Main){
                    //Toast.makeText(this@MainActivity,"token: ${response.token}",Toast.LENGTH_SHORT).show()
                    goToMenu()
                }
            }catch (e:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@MainActivity,"Error: ${e.message}",Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}