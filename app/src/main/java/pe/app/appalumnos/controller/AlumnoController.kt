package pe.app.appalumnos.controller

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import pe.app.appalumnos.model.Alumno

class AlumnoController {

    // Referencia a la base de datos Realtime
    private val database = FirebaseDatabase.getInstance()
    // "alumnos" será el nodo raíz donde se guarden los datos
    private val ref = database.getReference("alumnos")

    fun add(alumno: Alumno, onSuccess: () -> Unit, onError: (String) -> Unit) {
        // En Realtime Database usamos push() para generar un ID único
        val newRef = ref.push()
        val id = newRef.key // Obtenemos el ID generado

        if (id != null) {
            alumno.id = id
            newRef.setValue(alumno)
                .addOnSuccessListener { onSuccess() }
                .addOnFailureListener { onError(it.message ?: "Error al guardar") }
        } else {
            onError("No se pudo generar el ID")
        }
    }

    fun update(alumno: Alumno, onSuccess: () -> Unit, onError: (String) -> Unit) {
        // Actualizamos el nodo específico usando el ID del alumno
        ref.child(alumno.id).setValue(alumno)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onError(it.message ?: "Error al actualizar") }
    }

    fun delete(id: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        // Borramos el nodo hijo correspondiente al ID
        ref.child(id).removeValue()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onError(it.message ?: "Error al eliminar") }
    }

    fun getAll(onSuccess: (ArrayList<Alumno>) -> Unit, onError: (String) -> Unit) {
        // addListenerForSingleValueEvent lee los datos una sola vez (como un SELECT)
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lista = ArrayList<Alumno>()

                for (child in snapshot.children) {
                    // Convertimos el JSON al objeto Alumno
                    val alumno = child.getValue(Alumno::class.java)
                    if (alumno != null) {
                        // Aseguramos que el objeto tenga su ID (la clave del nodo)
                        alumno.id = child.key ?: ""
                        lista.add(alumno)
                    }
                }
                onSuccess(lista)
            }

            override fun onCancelled(error: DatabaseError) {
                onError(error.message)
            }
        })
    }
}