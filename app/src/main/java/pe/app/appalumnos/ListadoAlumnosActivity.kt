package pe.app.appalumnos

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import pe.app.appalumnos.adapter.AlumnosAdapter
import pe.app.appalumnos.controller.AlumnoController

class ListadoAlumnosActivity : AppCompatActivity() {

    private lateinit var fabAdd: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AlumnosAdapter
    private val controller = AlumnoController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_alumnos) // Reutilizando layout lista

        fabAdd = findViewById(R.id.fab_add_alumno)
        recyclerView = findViewById(R.id.recycler_view_alumnos)

        // Inicializar con lista vacía
        adapter = AlumnosAdapter(ArrayList()) { alumno ->
            navigateToForm(alumno)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        fabAdd.setOnClickListener {
            navigateToForm(null) // null indica nuevo registro
        }
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        controller.getAll(
            onSuccess = { lista ->
                adapter.updateData(lista)
            },
            onError = { error ->
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun navigateToForm(alumno: pe.app.appalumnos.model.Alumno?) {
        val intent = Intent(this, RegistroAlumnosActivity::class.java)
        if (alumno != null) {
            intent.putExtra("ALUMNO_DATA", alumno)
        }
        startActivity(intent)
    }
}