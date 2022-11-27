package com.example.ddesc_aplicativo

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.ddesc_aplicativo.databinding.ActivityPrincipalBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.nio.file.attribute.AclEntry.Builder

class PrincipalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPrincipalBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var auth: FirebaseAuth




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root

        setContentView(view)
        auth = Firebase.auth


        drawerLayout = findViewById(R.id.drawer_layer)

        /* binding.botaoGraficos.setOnClickListener {
            val intent = Intent(this, TelaGraficos::class.java)
            startActivity(intent)
            finish()
        }*/

    }

    public fun ClickMenu(view: View){openDrawer(drawerLayout)}

    private fun openDrawer(drawerLayout: DrawerLayout){
        drawerLayout.openDrawer(GravityCompat.START)


    }

    public fun dashboard(view: View){
        val intent = Intent(this, PrincipalActivity::class.java)
        startActivity(intent)

    }

    public fun configuracoes(view: View){
        val intent = Intent(this, ConfiguracoesActivity::class.java)
        startActivity(intent)

    }

    public fun logout(view: View){
        logoutMenu(this)
    }

    private fun logoutMenu(principalActivity: PrincipalActivity){
       val builder = AlertDialog.Builder(principalActivity)
        builder.setTitle("Sair")
        builder.setMessage("Tem certeza que quer sair?")
        builder.setPositiveButton("Sim"){ dialogInterface, it ->
            finish()
        }
        builder.setNegativeButton("Não"){ dialogInterface, it ->
            dialogInterface.dismiss()

        }
        builder.show()
    }



    }

