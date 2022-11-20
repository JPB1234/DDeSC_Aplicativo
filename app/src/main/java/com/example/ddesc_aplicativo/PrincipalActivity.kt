package com.example.ddesc_aplicativo

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ddesc_aplicativo.databinding.ActivityPrincipalBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class PrincipalActivity : AppCompatActivity() {
    companion object{
        const val RESULT = "RESULT"
    }

    private lateinit var binding: ActivityPrincipalBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
        auth = Firebase.auth

        binding.botaoSair.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.botaoGraficos.setOnClickListener {
            val intent = Intent(this, TelaGraficos::class.java)
            startActivity(intent)
            finish()
        }

        binding.botaoLeitor.setOnClickListener {
            val intent = Intent(applicationContext, TelaLeitorCamera::class.java)
            startActivity(intent)
            finish()
        }

        val result = intent.getStringExtra(RESULT)

        if(result != null){
            if(result.contains("https://") || result.contains("http://")){
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(result))
                startActivity(intent)
            } else{
                binding.result.text = result.toString()
            }
        }
    }
}