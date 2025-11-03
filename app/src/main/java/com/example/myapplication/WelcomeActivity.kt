package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)

        // --- INICIO DE LA LÓGICA CORREGIDA ---

        // 1. Conectar las variables del código con los botones del diseño XML
        val loginButton: Button = findViewById(R.id.btnLogin)
        val registerText: TextView = findViewById(R.id.txtGoRegister)

        // 2. Asignar la acción para el botón "Iniciar sesión"
        loginButton.setOnClickListener {
            // Este código se ejecuta cuando el usuario pulsa el botón "btnLogin"
            Toast.makeText(this, "Yendo a la pantalla de Login...", Toast.LENGTH_SHORT).show()

            // Crear una "intención" para abrir la pantalla de Login
            val intent = Intent(this, LoginActivity::class.java)

            // Ejecutar la intención (abrir la pantalla)
            startActivity(intent)
        }

        // 3. Asignar la acción para el texto "Regístrate"
        registerText.setOnClickListener {
            // Este código se ejecuta cuando el usuario pulsa el texto "txtGoRegister"
            Toast.makeText(this, "Yendo a la pantalla de Registro...", Toast.LENGTH_SHORT).show()

            // Aquí pondrías el código para ir a tu actividad de registro
            // val intent = Intent(this, RegisterActivity::class.java)
            // startActivity(intent)
        }

        // --- FIN DE LA LÓGICA CORREGIDA ---

        // Este código para los márgenes ya lo tenías, lo dejamos igual.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
