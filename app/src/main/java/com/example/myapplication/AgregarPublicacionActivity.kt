package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.net.Uri // NUEVO: Necesario para manejar la dirección de la imagen.
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView // NUEVO: Necesario para la vista previa de la imagen.
import androidx.activity.result.contract.ActivityResultContracts // NUEVO: El método moderno para obtener resultados.
import androidx.appcompat.app.AppCompatActivity

class AgregarPublicacionActivity : AppCompatActivity() {

    // NUEVO: Variables para guardar la URI de la imagen y para la vista previa.
    private var imagenUri: Uri? = null
    private lateinit var imgPrevia: ImageView

    // NUEVO: Este es el manejador moderno de Android para recibir un resultado del selector de imágenes.
    private val selectorDeImagen = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        // Este bloque se ejecuta cuando el usuario selecciona una imagen de la galería.
        uri?.let {
            // Si el usuario seleccionó una imagen (la URI no es nula):
            // 1. Guardamos la URI en nuestra variable.
            imagenUri = it
            // 2. Mostramos la imagen en el ImageView de vista previa.
            imgPrevia.setImageURI(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_publicacion2)

        val edtContenido: EditText = findViewById(R.id.edtContenidoPublicacion)
        val btnPublicar: Button = findViewById(R.id.btnPublicar)

        // NUEVO: Conectamos los nuevos elementos del layout con variables en el código.
        val btnSeleccionarImagen: Button = findViewById(R.id.btnSeleccionarImagen)
        imgPrevia = findViewById(R.id.imgPrevia)

        // NUEVO: Lógica para el botón de "Seleccionar Imagen".
        btnSeleccionarImagen.setOnClickListener {
            // Al hacer clic, lanzamos el selector de contenido del sistema,
            // especificando que solo queremos ver archivos de tipo "imagen".
            selectorDeImagen.launch("image/*")
        }

        // MODIFICADO: La lógica del botón "Publicar" ahora también maneja la imagen.
        btnPublicar.setOnClickListener {
            val contenido = edtContenido.text.toString()

            // Ahora la condición es que haya texto O que se haya seleccionado una imagen.
            if (contenido.isNotEmpty() || imagenUri != null) {
                // 1. Crear un Intent para devolver los datos (sin cambios).
                val resultadoIntent = Intent()

                // 2. Añadir el texto del post como un "extra".
                resultadoIntent.putExtra("NUEVO_POST_CONTENIDO", contenido)

                // NUEVO: Añadir también la URI de la imagen como un "extra".
                // La convertimos a String porque es la forma más fácil de pasarla entre actividades.
                resultadoIntent.putExtra("NUEVO_POST_IMAGEN_URI", imagenUri?.toString())

                // 3. Establecer el resultado como OK y enviar el Intent de vuelta (sin cambios).
                setResult(Activity.RESULT_OK, resultadoIntent)

                // 4. Cerrar esta actividad para volver a la anterior (sin cambios).
                finish()
            }
        }
    }
}
