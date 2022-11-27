package com.example.ddesc_aplicativo.ui.sair

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.ddesc_aplicativo.LoginActivity
import com.example.ddesc_aplicativo.MainActivity
import com.example.ddesc_aplicativo.databinding.FragmentSairBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SairFragment : Fragment() {
    private lateinit var binding: FragmentSairBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        auth = Firebase.auth
        binding = FragmentSairBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.BotaoDeslogar.setOnClickListener {

            Toast.makeText(context,"Usuário deslogado", Toast.LENGTH_SHORT).show()
            auth.signOut()
            val i = Intent(activity, LoginActivity::class.java)
            startActivity(i)
            activity?.finish()
        }

        binding.BotaoVoltar.setOnClickListener {
            val i = Intent(activity, MainActivity::class.java)
            startActivity(i)
            activity?.finish()
        }


    }


}