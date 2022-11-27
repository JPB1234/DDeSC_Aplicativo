package com.example.ddesc_aplicativo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ConfiguracoesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracoes)
    }


    public fun voltar(view: View){
        val intent = Intent(this, PrincipalActivity::class.java)
        startActivity(intent)
        finish()
    }


}