package com.example.ddesc_aplicativo

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ddesc_aplicativo.databinding.ActivityEsqueciASenhaBinding
import com.google.firebase.auth.FirebaseAuth

class EsqueciASenhaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEsqueciASenhaBinding
    private val TAG = "EsqueciASenhaActivity"
    private var etEmail: EditText? = null
    private var btnSubmit: Button? = null


    private var mAuth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEsqueciASenhaBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        binding.botaoVoltarTelaLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()

        }

        inicialise()
    }

    private fun inicialise(){
        etEmail = findViewById(R.id.et_email) as EditText
        btnSubmit = findViewById(R.id.btn_submit) as Button

        mAuth = FirebaseAuth.getInstance()
        btnSubmit!!.setOnClickListener { sendPasswordEmail() }

    }

    private fun sendPasswordEmail(){
        val email = etEmail?.text.toString()

        if (!TextUtils.isEmpty(email)){
            mAuth!!
                .sendPasswordResetEmail(email)
                .addOnCompleteListener{task ->
                    if (task.isSuccessful){
                        val message = "Email enviado"
                        Log.d(TAG, message)
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                        updateUI()
                    } else{
                        Log.w(TAG, task.exception!!.message.toString())
                        Toast.makeText(this, "Nenhum usuário encontrado com esse email", Toast.LENGTH_SHORT).show()
                    }
                }
        } else{
            Toast.makeText(this, "Entre com um Email válido", Toast.LENGTH_SHORT).show()
        }

    }
    private fun updateUI(){
        val intent = Intent(this@EsqueciASenhaActivity, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }


}