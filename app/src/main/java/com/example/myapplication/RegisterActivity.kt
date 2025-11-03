package com.example.myapplication

// --- IMPORTACIONES NECESARIAS ---
// Asegúrate de que todas estas importaciones estén presentes
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    // 1. Declara la instancia de FirebaseAuth para comunicarte con Firebase
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // 2. Inicializa la instancia de FirebaseAuth
        mAuth = FirebaseAuth.getInstance()

        // 3. Conecta las variables del código con las vistas del XML
        val nameEditText: EditText = findViewById(R.id.edtName)
        val emailEditText: EditText = findViewById(R.id.edtEmail)
        val passwordEditText: EditText = findViewById(R.id.edtPassword)
        val confirmPasswordEditText: EditText = findViewById(R.id.edtConfirmPassword)
        val registerButton: Button = findViewById(R.id.btnRegister)
        val goLoginTextView: TextView = findViewById(R.id.txtGoLogin)

        // 4. Configura el listener para el BOTÓN DE REGISTRO
        registerButton.setOnClickListener {
            // Este bloque se ejecuta cuando el usuario pulsa "Registrarme"

            // Obtiene el texto de los campos
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString().trim() // .trim() quita espacios
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()

            // 5. Valida los datos ingresados por el usuario
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener // Detiene la ejecución si hay campos vacíos
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener // Detiene la ejecución si no son iguales
            }

            if (password.length < 6) {
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
                return@setOnClickListener // Firebase requiere 6 caracteres como mínimo
            }

            // 6. Si todas las validaciones pasan, llama a Firebase para crear el usuario
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // El registro fue exitoso
                        Toast.makeText(this, "¡Registro exitoso!", Toast.LENGTH_SHORT).show()

                        // Opcional: Navega a la pantalla de login después del registro
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish() // Cierra la actividad de registro para que no pueda volver

                    } else {
                        // Si el registro falla, muestra un mensaje detallado al usuario.
                        Toast.makeText(this, "Falló el registro: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                    }
                }
        }

        // 7. Configura el listener para el texto "Inicia sesión"
        goLoginTextView.setOnClickListener {
            // Navega de vuelta a la actividad de Login
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Cierra esta pantalla
        }
    }
}
