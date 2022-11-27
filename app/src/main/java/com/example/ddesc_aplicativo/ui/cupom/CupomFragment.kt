package com.example.ddesc_aplicativo.ui.cupom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.ddesc_aplicativo.databinding.FragmentCupomBinding


class CupomFragment : Fragment() {

    private lateinit var binding: FragmentCupomBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentCupomBinding.inflate(inflater, container, false)
        return binding.root
    }

}