package com.example.ddesc_aplicativo

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ddesc_aplicativo.databinding.ActivityFormCadastroBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException




class CadastroActivity : AppCompatActivity(){
    private lateinit var binding: ActivityFormCadastroBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFormCadastroBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        binding.botaoConfirmarCadastro.setOnClickListener{view ->
            val nome = binding.editTextNomeDeUsuario.text.toString()
            val email = binding.editTextEmailDeCadastro.text.toString()
            val senha = binding.editTextSenhaDeCadastro.text.toString()
            val confirmarSenha = binding.editTextConfirmarSenhaDeCadastro.text.toString()

            if(nome.isEmpty() || email.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()){
                val snackbar = Snackbar.make(view, "Preencha todos os campos!", Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            }else if (senha == confirmarSenha){
                auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener { cadastro ->
                    if(cadastro.isSuccessful){
                        val snackbar = Snackbar.make(view, "Sucesso ao cadastrar o usuario!", Snackbar.LENGTH_SHORT)
                        snackbar.setBackgroundTint(Color.BLUE)
                        snackbar.show()
                        binding.editTextNomeDeUsuario.setText("")
                        binding.editTextEmailDeCadastro.setText("")
                        binding.editTextSenhaDeCadastro.setText("")
                        binding.editTextConfirmarSenhaDeCadastro.setText("")
                        abrePrincipal();
                    }
                }.addOnFailureListener{exception ->
                    val mensagemError = when(exception){
                        is FirebaseAuthWeakPasswordException -> "Digite uma senha com no mínimo 6 caracteres"
                        is FirebaseAuthInvalidCredentialsException -> "Digite um email válido"
                        is FirebaseAuthUserCollisionException -> "Esta conta já foi cadastrada"
                        is FirebaseNetworkException -> "Sem conexão com a internet"
                        else -> "Erro ao cadastrar usuario!"
                    }
                    val snackbar = Snackbar.make(view, mensagemError, Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.RED)
                    snackbar.show()
                }
            }else{
                val snackbar = Snackbar.make(view, "A senha e confirmação de senha devem ser idênticas!", Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            }
        }

        binding.botaoVoltarParaMenu.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)

            startActivity(intent)
            finish()
        }
    }

    fun abrePrincipal(){
        Toast.makeText(baseContext, "Autenticação efetuada com sucesso.",
            Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}