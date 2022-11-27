package com.example.ddesc_aplicativo.ui.configuracoes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ddesc_aplicativo.R
import com.example.ddesc_aplicativo.databinding.FragmentConfiguracoesBinding


class ConfiguracoesFragment : Fragment() {

    private lateinit var binding: FragmentConfiguracoesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConfiguracoesBinding.inflate(inflater, container, false)
        return binding.root


    }


}