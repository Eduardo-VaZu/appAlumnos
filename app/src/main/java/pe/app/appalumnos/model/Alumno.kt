package pe.app.appalumnos.model

import java.io.Serializable

data class Alumno(
    var id: String = "",
    var nombre: String = "",
    var apellido: String = ""
): Serializable