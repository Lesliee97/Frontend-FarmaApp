package com.ejemplo.login.vieww

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ejemplo.login.R
import com.ejemplo.login.databinding.TopMenuItemBinding


class RVTopMenuAdapter : RecyclerView.Adapter<RVTopMenuAdapter.ViewHolder>() {

    private var productoList: MutableList<Productos> = mutableListOf()

    fun addAll(productoList:List<Productos>){
        this.productoList.addAll(productoList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            layoutInflater.inflate(
                R.layout.top_menu_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productos = productoList[position]
        holder.bind(productos)
    }

    override fun getItemCount(): Int = productoList.size


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = TopMenuItemBinding.bind(view)

        fun bind(productos: Productos) {
            binding.ivTopMenuItemPizza.setImageResource(productos.imagen)
            binding.tvTopMenuItemName.text = productos.name
            binding.tvTopMenuItemType.text = productos.category
            binding.tvTopMenuItemPrice.text = String.format(
                format = itemView.context.resources.getString(R.string.format_price),
                args = arrayOf(productos.precio)
            )


        }
    }


}