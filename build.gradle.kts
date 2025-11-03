//Archivo de compilación de nivel superior donde puede agregar opciones de configuración comunes a todos los subproyectos/módulos.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false

    id("com.google.gms.google-services") version "4.4.4" apply false
}
