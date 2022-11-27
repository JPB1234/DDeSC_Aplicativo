package com.example.ddesc_aplicativo.ui.lixeira

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ddesc_aplicativo.R
import com.example.ddesc_aplicativo.databinding.FragmentLixeiraBinding


class LixeiraFragment : Fragment() {
    private lateinit var binding: FragmentLixeiraBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{

        binding = FragmentLixeiraBinding.inflate(inflater, container, false)
        return binding.root
    }


}