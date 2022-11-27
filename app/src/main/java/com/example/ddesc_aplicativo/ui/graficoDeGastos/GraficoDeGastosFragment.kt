package com.example.ddesc_aplicativo.ui.graficoDeGastos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ddesc_aplicativo.databinding.FragmentGraficoDeGastosBinding

class GraficoDeGastosFragment : Fragment() {
    private lateinit var binding: FragmentGraficoDeGastosBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGraficoDeGastosBinding.inflate(inflater, container, false)
        return binding.root
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtGraficoDeGastos.text = "Gráfico de seus gastos"
    }

    }


