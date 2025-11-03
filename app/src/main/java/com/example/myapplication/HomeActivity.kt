package com.example.myapplication

// --- IMPORTACIONES REQUERIDAS ---
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
// <-- ¡AÑADE ESTA IMPORTACIÓN!

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home) // Carga el diseño activity_home.xml

        // --- INICIO DE LA LÓGICA DE NAVEGACIÓN ---

        // 1. Conectar la variable "tarjetaMusica" con el LinearLayout del XML que tiene el ID "btnMusica".
        val tarjetaMusica: LinearLayout = findViewById(R.id.btnMusica)

        // 2. Asignar una acción de clic. El código dentro de las llaves {} se ejecutará al presionar la tarjeta.
        tarjetaMusica.setOnClickListener {

            // 3. Crear una "intención" (Intent) para abrir la nueva actividad.
            // Le decimos al sistema: "Desde esta pantalla (this), quiero ir a PantallaInicioActivity".
            val intent = Intent(this, PantallaInicioActivity::class.java) // <-- ¡AÑADE EL NOMBRE AQUÍ!

            // 4. Ejecutar la intención para abrir la nueva pantalla.
            startActivity(intent)
        }

        // --- FIN DE LA LÓGICA DE NAVEGACIÓN ---
    }
}
