package pe.app.appalumnos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pe.app.appalumnos.R
import pe.app.appalumnos.model.Alumno

class AlumnosAdapter(
    var data: ArrayList<Alumno>,
    private val onItemClicked: (Alumno) -> Unit
) : RecyclerView.Adapter<AlumnosAdapter.AlumnoViewHolder>() {

    // Clase interna ViewHolder
    class AlumnoViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val textNombre: TextView = item.findViewById(R.id.text_nombre) // Reutilizamos ID del layout anterior o crea uno nuevo text_nombre
        val textApellido: TextView = item.findViewById(R.id.text_apellido) // Reutilizamos ID text_apellido

        // Puedes ocultar los elementos que no uses del layout original (chip, imagen) en el XML
    }

    fun updateData(newData: ArrayList<Alumno>) {
        data = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlumnoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_list_alumno, parent, false) // Usa tu layout de tarjeta
        return AlumnoViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlumnoViewHolder, position: Int) {
        val alumno = data[position]

        holder.textNombre.text = alumno.nombre
        holder.textApellido.text = alumno.apellido

        holder.itemView.setOnClickListener {
            onItemClicked(alumno)
        }
    }

    override fun getItemCount(): Int = data.size
}