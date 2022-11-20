package com.example.ddesc_aplicativo

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.ddesc_aplicativo.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//claudio
        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root

        setContentView(view)

        auth = Firebase.auth


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken("484893052479-52rfh3ve9s4nacelu44869tou839h7ie.apps.googleusercontent.com").requestEmail().build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        //Botão de Entrar para logar
        binding.botaoEntrar.setOnClickListener {
            if(TextUtils.isEmpty(binding.editTextUsuario.text)){
                binding.editTextUsuario.error = "Por favor, preencha o nome de usuário"
            }else if(TextUtils.isEmpty(binding.editTextSenha.text)){
                binding.editTextSenha.error = "Por favor, preencha a senha"
            }else {


                loginUsuarioESenha(
                    binding.editTextUsuario.text.toString(),
                    binding.editTextSenha.text.toString()
                )
            }


        }
        //Botão de entrar como convidado
        binding.botaoConvidado.setOnClickListener {
            val intent = Intent(this, PrincipalActivity::class.java)
            startActivity(intent)
            finish()

        }
        //Botão do google
        binding.botaoGoogle.setOnClickListener{
            signIn()
        }

        //Botão de cadastro
        binding.botaoCadastro.setOnClickListener{
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
    private fun signIn(){
        val intent = googleSignInClient.signInIntent
        abreActivity.launch(intent)

    }
    var abreActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: ActivityResult ->

        if(result.resultCode == RESULT_OK){
            val intent = result.data
            val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
            try {
                val conta = task.getResult(ApiException::class.java)
                loginComGoogle(conta.idToken!!)
            }catch (exception: ApiException){

            }
        }

    }

    private fun loginComGoogle(token: String){
        val credencial = GoogleAuthProvider.getCredential(token, null)
        auth.signInWithCredential(credencial).addOnCompleteListener(this){
            task: Task<AuthResult> ->
            if (task.isSuccessful){
                Toast.makeText(
                    baseContext, "Autenticação efetuada com sucesso.",
                    Toast.LENGTH_SHORT
                ).show()
                abrePrincipal()
            }else{
                Toast.makeText(
                    baseContext, "Erro na autenticação com Google, tente novamente.",
                    Toast.LENGTH_SHORT
                ).show()

            }
        }

    }

    private fun loginUsuarioESenha(usuario: String, senha: String) {
        auth.signInWithEmailAndPassword(
           usuario,
            senha
        )
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {


                    val user = auth.currentUser
                    Toast.makeText(
                        baseContext, "Autenticação efetuada com sucesso.",
                        Toast.LENGTH_SHORT
                    ).show()
                    abrePrincipal()

                } else {

                    Log.w(TAG, "signInWithCustomToken:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Usuário e/ou Senha incorreto(s).",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
    }


    fun abrePrincipal(){
        Toast.makeText(baseContext, "Autenticação efetuada com sucesso.",
            Toast.LENGTH_SHORT).show()
        binding.editTextUsuario.text.clear()
        binding.editTextSenha.text.clear()
        val intent = Intent(this, PrincipalActivity::class.java)
        startActivity(intent)
        finish()
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            if(currentUser.email?.isNotEmpty() == true){
                Toast.makeText(baseContext, "Usuário " + currentUser.email + " já está logado",
                    Toast.LENGTH_SHORT).show()
                abrePrincipal()
            }
        }

        //updateUI(currentUser)
    }



}