package com.example.myapplication
//impotanciA NECESARIA DE LAS CUENTAS
// NUEVO: Importaciones necesarias para manejar el resultado de otra actividad y el nuevo layout.
import android.app.Activity
import android.content.Intent
import android.os.Bundle

import android.view.LayoutInflater


import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.cardview.widget.CardView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PantallaInicioActivity : AppCompatActivity() {

    // --- VARIABLES PARA GUARDAR EL ESTADO (las que ya tenías) ---
    private var contadorCorazones = 124
    private var leDiCorazon = false

    // NUEVO: Variable para tener una referencia al layout donde se añadirán las publicaciones.
    private lateinit var layoutContenido: LinearLayout

    // NUEVO: Este es el manejador moderno de Android para recibir un resultado de otra actividad.
    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        // Este bloque se ejecuta cuando la actividad 'AgregarPublicacionActivity' se cierra.
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            // Extraemos el texto de la nueva publicación que nos enviaron de vuelta.
            val nuevoContenido = data?.getStringExtra("NUEVO_POST_CONTENIDO")

            if (nuevoContenido != null) {
                // Si recibimos texto, llamamos a la función para crear y mostrar la nueva tarjeta.
                agregarNuevaPublicacion(nuevoContenido)
                Toast.makeText(this, "¡Publicación agregada!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla_inicio)

        // --- CONECTAR LAS VARIABLES DEL CÓDIGO CON LOS IDS DEL XML (las que ya tenías) ---
        val btnOpciones: ImageButton = findViewById(R.id.btnOpciones)
        val btnCorazon: ImageButton = findViewById(R.id.btnCorazon)
        val txtContadorCorazones: TextView = findViewById(R.id.txtContadorCorazones)
        val btnComentario: ImageButton = findViewById(R.id.btnComentario)

        // NUEVO: Conectar las nuevas variables con sus vistas en el XML.
        val fabAgregar: FloatingActionButton = findViewById(R.id.fab_add)
        layoutContenido = findViewById(R.id.contentLayout) // El LinearLayout que está dentro del ScrollView.

        // NUEVO: LÓGICA DEL BOTÓN FLOTANTE (+) PARA AGREGAR PUBLICACIÓN ---
        fabAgregar.setOnClickListener {
            // 1. Crear una intención para ir a la pantalla de agregar publicación.
            val intent = Intent(this, AgregarPublicacionActivity::class.java)

            // 2. Lanzar la actividad, pero indicando que esperamos un resultado de vuelta.
            startForResult.launch(intent)
        }

        // --- LÓGICA DEL BOTÓN DE OPCIONES (DOS PUNTOS) --- (Tu código original, sin cambios)
        btnOpciones.setOnClickListener { view ->
            val popupMenu = PopupMenu(this, view)
            popupMenu.menuInflater.inflate(R.menu.menu_opciones_post, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
                when (menuItem.itemId) {
                    R.id.opcion_reportar -> {
                        Toast.makeText(this, "Publicación reportada", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.opcion_ocultar -> {
                        Toast.makeText(this, "Publicación oculta", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }

        // --- LÓGICA DEL BOTÓN DE CORAZÓN --- (Tu código original, sin cambios)
        btnCorazon.setOnClickListener {
            leDiCorazon = !leDiCorazon
            if (leDiCorazon) {
                contadorCorazones++
                btnCorazon.setImageResource(R.drawable.corazon)
                btnCorazon.setColorFilter(getColor(R.color.red))
            } else {
                contadorCorazones--
                btnCorazon.setImageResource(R.drawable.favorito)
                btnCorazon.clearColorFilter()
            }
            txtContadorCorazones.text = contadorCorazones.toString()
        }

        // --- LÓGICA DEL BOTÓN DE COMENTARIO --- (Tu código original, sin cambios)
        btnComentario.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Escribe tu comentario")
            val input = EditText(this)
            builder.setView(input)
            builder.setPositiveButton("Enviar") { dialog, _ ->
                val comentario = input.text.toString()
                if (comentario.isNotEmpty()) {
                    Toast.makeText(this, "Comentario enviado: $comentario", Toast.LENGTH_LONG).show()
                }
                dialog.dismiss()
            }
            builder.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.cancel()
            }
            builder.show()
        }
    }

    // NUEVO: Esta función se encarga de crear dinámicamente una nueva tarjeta y añadirla a la pantalla.
    private fun agregarNuevaPublicacion(contenido: String) {
        // 1. Usar un "inflater" para crear una nueva vista de tarjeta a partir del layout 'tarjeta_publicacion_item.xml'.
        val nuevaTarjeta = LayoutInflater.from(this).inflate(R.layout.tarjeta_publicacion_item, layoutContenido, false) as CardView

        // 2. Encontrar el TextView DENTRO de la nueva tarjeta que acabamos de crear.
        val txtContenidoNuevo: TextView = nuevaTarjeta.findViewById(R.id.txtContenidoPublicacion)
        // (Aquí también podrías encontrar el TextView del nombre de usuario, la hora, etc., si quisieras personalizarlos).

        // 3. Establecer el contenido del post que recibimos de la otra actividad.
        txtContenidoNuevo.text = contenido

        // 4. Añadir la nueva tarjeta al LinearLayout principal. El '0' hace que se añada al principio (la más reciente arriba).
        layoutContenido.addView(nuevaTarjeta, 0)
    }
}
