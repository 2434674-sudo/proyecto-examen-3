package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        // Referencias a las tarjetas
        val btnMusica = findViewById<LinearLayout>(R.id.btnMusica)
        val btnArte = findViewById<LinearLayout>(R.id.btnArte)
        val btnHistorias = findViewById<LinearLayout>(R.id.btnHistorias)

        // Clics (por ahora solo muestra un ejemplo o abre otra actividad)
        btnMusica.setOnClickListener {
            // Ejemplo: abre una nueva activity o muestra un mensaje
            // startActivity(Intent(this, MusicaActivity::class.java))
        }

        btnArte.setOnClickListener {
            // startActivity(Intent(this, ArteActivity::class.java))
        }

        btnHistorias.setOnClickListener {
            // startActivity(Intent(this, HistoriasActivity::class.java))
        }
    }
}
