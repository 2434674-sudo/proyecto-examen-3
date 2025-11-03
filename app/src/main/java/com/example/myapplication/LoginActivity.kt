package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        // --- INICIO DE LA LÓGICA DE LOGIN ---

        // 1. Conectar las variables del código con las vistas del diseño XML.
        // Asumiendo que tus campos de texto tienen los IDs "txtEmail" y "txtPassword"
        val emailEditText: EditText = findViewById(R.id.txtEmail)
        val passwordEditText: EditText = findViewById(R.id.txtPassword)
        val loginButton: Button = findViewById(R.id.btnLogin)

        // 2. Asignar la acción que se ejecutará al pulsar el botón "Iniciar sesión"
        loginButton.setOnClickListener {
            // Este bloque se ejecuta solo cuando el usuario pulsa el botón

            // 3. Obtener el texto que el usuario escribió en los campos
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // 4. Hacer una validación simple de los datos
            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Si ambos campos tienen texto, el login es "exitoso" (por ahora)

                // En el futuro, aquí verificarías el usuario y contraseña
                // contra una base de datos o un servicio web.

                // Mostramos un mensaje de confirmación
                Toast.makeText(this, "¡Inicio de sesión exitoso!", Toast.LENGTH_SHORT).show()

                // 5. Crear la intención para navegar a HomeActivity
                val intent = Intent(this, HomeActivity::class.java)

                // Ejecutar la navegación
                startActivity(intent)

                // 6. (MUY RECOMENDADO) Cerrar la pantalla de Login para que el usuario
                // no pueda volver a ella con el botón "atrás" del teléfono.
                finish()

            } else {
                // Si al menos un campo está vacío, mostramos un mensaje de error
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        // --- FIN DE LA LÓGICA DE LOGIN ---

        // Este es el código que ya tenías para los márgenes de la pantalla. Lo mantenemos.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
