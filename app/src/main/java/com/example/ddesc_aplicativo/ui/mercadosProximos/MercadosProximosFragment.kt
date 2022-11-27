package com.example.ddesc_aplicativo.ui.mercadosProximos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ddesc_aplicativo.R
import com.example.ddesc_aplicativo.databinding.FragmentMercadosProximosBinding


class MercadosProximosFragment : Fragment() {

    private lateinit var binding: FragmentMercadosProximosBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMercadosProximosBinding.inflate(inflater, container, false)
        return binding.root
    }


}