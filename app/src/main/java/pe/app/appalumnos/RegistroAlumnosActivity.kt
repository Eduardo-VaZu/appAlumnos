package pe.app.appalumnos

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import pe.app.appalumnos.controller.AlumnoController
import pe.app.appalumnos.model.Alumno

class RegistroAlumnosActivity : AppCompatActivity() {

    private lateinit var etNombre: TextInputEditText
    private lateinit var etApellido: TextInputEditText
    private lateinit var btnSave: Button
    private lateinit var btnBack: Button
    private lateinit var btnDelete: Button

    private var alumnoActual: Alumno? = null
    private val controller = AlumnoController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.form_alumno) // Ajusta si cambiaste el nombre del layout

        // Asegúrate que estos IDs existan en tu XML
        etNombre = findViewById(R.id.edit_text_nombre) // Usando ID existente como ejemplo, ideal cambiarlo a edit_text_nombre
        etApellido = findViewById(R.id.edit_text_apellido) // Usando ID existente como ejemplo

        btnSave = findViewById(R.id.button_add)
        btnBack = findViewById(R.id.button_back)
        btnDelete = findViewById(R.id.button_delete)

        // Recibir objeto completo (Serializable)
        alumnoActual = intent.getSerializableExtra("ALUMNO_DATA") as? Alumno

        if (alumnoActual != null) {
            etNombre.setText(alumnoActual!!.nombre)
            etApellido.setText(alumnoActual!!.apellido)
            btnSave.text = "Actualizar"
            btnDelete.visibility = View.VISIBLE
        }

        btnSave.setOnClickListener { saveOrUpdate() }
        btnBack.setOnClickListener { finish() }
        btnDelete.setOnClickListener { showDeleteConfirm() }
    }

    private fun saveOrUpdate() {
        val nombre = etNombre.text.toString()
        val apellido = etApellido.text.toString()

        if (nombre.isEmpty() || apellido.isEmpty()) {
            showAlert("Error", "Complete los campos", false)
            return
        }

        if (alumnoActual == null) {
            // Crear nuevo
            val nuevoAlumno = Alumno(nombre = nombre, apellido = apellido)
            controller.add(nuevoAlumno,
                onSuccess = {
                    showAlert("Éxito", "Alumno registrado", true)
                },
                onError = { showAlert("Error", it, false) }
            )
        } else {
            // Actualizar existente
            alumnoActual!!.nombre = nombre
            alumnoActual!!.apellido = apellido
            controller.update(alumnoActual!!,
                onSuccess = {
                    showAlert("Éxito", "Alumno actualizado", true)
                },
                onError = { showAlert("Error", it, false) }
            )
        }
    }

    private fun deleteAlumno() {
        alumnoActual?.let {
            controller.delete(it.id,
                onSuccess = {
                    showAlert("Éxito", "Alumno eliminado", true)
                },
                onError = { showAlert("Error", it, false) }
            )
        }
    }

    private fun showDeleteConfirm() {
        AlertDialog.Builder(this)
            .setTitle("Confirmar")
            .setMessage("¿Eliminar este alumno?")
            .setPositiveButton("Sí") { _, _ -> deleteAlumno() }
            .setNegativeButton("No", null)
            .show()
    }

    private fun showAlert(titulo: String, mensaje: String, closeActivity: Boolean) {
        val builder = AlertDialog.Builder(this)
            .setTitle(titulo)
            .setMessage(mensaje)
            .setPositiveButton("OK") { _, _ ->
                if (closeActivity) finish()
            }
        builder.create().show()
    }
}