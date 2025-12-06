package pe.app.appalumnos.controller

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import pe.app.appalumnos.model.Alumno

class AlumnoController {
    private val database = FirebaseDatabase.getInstance()
    private val ref = database.getReference("alumnos")

    fun add(alumno: Alumno, onSuccess: () -> Unit, onError: (String) -> Unit) {
        val newRef = ref.push()
        val id = newRef.key

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
        ref.child(alumno.id).setValue(alumno)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onError(it.message ?: "Error al actualizar") }
    }

    fun delete(id: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        ref.child(id).removeValue()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onError(it.message ?: "Error al eliminar") }
    }

    fun getAll(onSuccess: (ArrayList<Alumno>) -> Unit, onError: (String) -> Unit) {
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lista = ArrayList<Alumno>()

                for (child in snapshot.children) {
                    val alumno = child.getValue(Alumno::class.java)
                    if (alumno != null) {
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