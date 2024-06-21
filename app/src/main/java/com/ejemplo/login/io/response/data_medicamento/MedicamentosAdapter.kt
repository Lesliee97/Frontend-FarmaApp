package com.ejemplo.login.io.response.data_medicamento

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ejemplo.login.R
import com.ejemplo.login.databinding.TopMenuItemBinding
import com.ejemplo.login.vieww.Productos


class MedicamentosAdapter : RecyclerView.Adapter<MedicamentosAdapter.MedicamentoViewHolder>()  {

    private val medicamentos = mutableListOf<MedicamentosResponse>()

    fun addAll(medicamentos:List<MedicamentosResponse>){
        this.medicamentos.addAll(medicamentos)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicamentoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MedicamentoViewHolder(
            layoutInflater.inflate(
                R.layout.item_stock,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MedicamentoViewHolder, position: Int) {
        val medicamento = medicamentos[position]
        holder.bind(medicamento)
    }

    override fun getItemCount(): Int {
        return medicamentos.size
    }



    class MedicamentoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = TopMenuItemBinding.bind(view)

        fun bind(medicamento: MedicamentosResponse) {
            //binding.tvTopMenuItemNombre.text = medicamento.nombre
            //binding.tvTopMenuItemformaFarmaceutica.text = medicamento.formaFarmaceutica
            //binding.tvTopMenuItemPrecioUnitario.text = String.format(
                //format = itemView.context.resources.getString(R.string.format_price),
                //args = arrayOf(medicamentos.precio)
            //)
            //binding.tvTopMenuItemPrecioUnitario.text = String.format(
                //format = itemView.context.resources.getString(R.string.format_price),
                //args = arrayOf(medicamentos.precio)
            //)
            //.tvTopMenuItemNombreCategoria.text = medicamento.formaFarmaceutica

        }
    }

}