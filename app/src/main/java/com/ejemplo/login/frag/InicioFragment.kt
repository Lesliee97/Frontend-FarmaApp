package com.ejemplo.login.frag

import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ejemplo.login.databinding.FragmentInicioBinding
import com.ejemplo.login.vieww.DataDummy
import com.ejemplo.login.vieww.RVTopMenuAdapter
import kotlinx.coroutines.launch

class InicioFragment : Fragment() {
    /*
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inicio, container, false)
    }
    */
    //private val PromoName = ViewBindings.findChildViewById<TextView>(R.id.)

    private var _binding: FragmentInicioBinding? = null
    private val binding get() = _binding!!

    private val rvTopMenuAdapter = RVTopMenuAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configure the TextView for the discount price
        binding.tvPizzaMenuHotPromoDiscountPrice.apply {
            text = "$ 7.98"
            paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }

        // Set up the RecyclerView
        setRV()
/*
        binding.tvPizzaMenuHotPromoName.setOnClickListener {
            val intent = Intent(requireContext(), ProductoDetailActivity::class.java)
            startActivity(intent)
        }
        binding.ivPizzaMenuHotPromoPizza.setOnClickListener {
            val intent = Intent(requireContext(), ProductoDetailActivity::class.java)
            startActivity(intent)
        }*/
    }

    private fun setRV() {
        lifecycleScope.launch {
            val productos = DataDummy.getTopMenuProductos(requireContext())

            rvTopMenuAdapter.addAll(productos)

            binding.rvPizzaMenu.apply {
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    RecyclerView.HORIZONTAL,
                    false
                )
                adapter = rvTopMenuAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}