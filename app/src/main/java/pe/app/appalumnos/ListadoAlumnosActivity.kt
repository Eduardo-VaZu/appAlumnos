package pe.app.appalumnos

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import pe.app.appalumnos.adapter.AlumnosAdapter
import pe.app.appalumnos.controller.AlumnoController
import pe.app.appalumnos.model.Alumno
import java.util.Locale

class ListadoAlumnosActivity : AppCompatActivity() {

    private lateinit var fabAdd: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var adapter: AlumnosAdapter
    private val controller = AlumnoController()
    private var listaCompleta = ArrayList<Alumno>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_alumnos)

        fabAdd = findViewById(R.id.fab_add_alumno)
        recyclerView = findViewById(R.id.recycler_view_alumnos)
        searchView = findViewById(R.id.search_view_alumno)

        adapter = AlumnosAdapter(ArrayList()) { alumno ->
            navigateToForm(alumno)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        fabAdd.setOnClickListener {
            navigateToForm(null)
        }
        setupSearchView()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        controller.getAll(
            onSuccess = { lista ->
                listaCompleta = lista
                adapter.updateData(lista)
            },
            onError = { error ->
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })
    }

    private fun filterList(query: String?) {
        if (query != null) {
            val listaFiltrada = ArrayList<Alumno>()
            val busqueda = query.lowercase(Locale.ROOT)

            for (alumno in listaCompleta) {
                if (alumno.nombre.lowercase(Locale.ROOT).contains(busqueda) ||
                    alumno.apellido.lowercase(Locale.ROOT).contains(busqueda)) {
                    listaFiltrada.add(alumno)
                }
            }
            adapter.updateData(listaFiltrada)
        }
    }

    private fun navigateToForm(alumno: Alumno?) {
        val intent = Intent(this, RegistroAlumnosActivity::class.java)
        if (alumno != null) {
            intent.putExtra("ALUMNO_DATA", alumno)
        }
        startActivity(intent)
    }
}