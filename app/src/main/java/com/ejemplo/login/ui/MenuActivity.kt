package com.ejemplo.login.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ejemplo.login.R
import com.ejemplo.login.util.PreferenceHelper
import com.ejemplo.login.util.PreferenceHelper.set

import android.view.MenuItem
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.ejemplo.login.frag.ComprasFragment
import com.ejemplo.login.frag.FavoritosFragment
import com.ejemplo.login.frag.InicioFragment
import com.ejemplo.login.frag.NotificacionesFragment
import com.ejemplo.login.frag.PerfilFragment
import com.ejemplo.login.frag.RecetaFragment
import com.google.android.material.navigation.NavigationView

class MenuActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        drawerLayout = findViewById<DrawerLayout>(R.id.main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, InicioFragment()).commit()
            navigationView.setCheckedItem(R.id.nav_inicio)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_inicio -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, InicioFragment()).commit()
            R.id.nav_perfil -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, PerfilFragment()).commit()
            R.id.nav_receta -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RecetaFragment()).commit()
            R.id.nav_notificaciones -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, NotificacionesFragment()).commit()
            R.id.nav_compras -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ComprasFragment()).commit()
            R.id.nav_favoritos -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FavoritosFragment()).commit()
            R.id.nav_salir -> {
                // Limpiar sesión y redirigir al login
                clearSesionPreference()
                goLogin()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    private fun goLogin(){
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        finish()
    }

    private fun clearSesionPreference(){
        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["token"] = ""
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }


}
/*class MenuActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btn_cerrar_sesion = findViewById<Button>(R.id.btn_cerrar_sesion)
        btn_cerrar_sesion.setOnClickListener{
            clearSesionPreference()
            goLogin()
        }
    }
    private fun goLogin(){
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        finish()
    }

    private fun clearSesionPreference(){
        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["token"] = ""
    }
}*/