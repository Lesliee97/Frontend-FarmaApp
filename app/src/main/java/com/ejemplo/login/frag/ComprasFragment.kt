package com.ejemplo.login.frag

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ejemplo.login.R
import com.ejemplo.login.databinding.FragmentComprasBinding
import com.ejemplo.login.io.response.data_medicamento.InicioViewModel

class ComprasFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compras, container, false)
    }


}
