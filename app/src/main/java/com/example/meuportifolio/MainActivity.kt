package com.example.meuportifolio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private val autenticacao = FirebaseAuth.getInstance()
    lateinit var btnConhece: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        iniciando()
        btnConhece.setOnClickListener {
            startActivity(Intent(this, TelaCadastro::class.java))
        }
    }

    override fun onStart() {
        super.onStart()

        val sim = autenticacao.currentUser
        if (sim != null){
            startActivity(Intent(this, TelaposLogin::class.java))
        }

    }

    private fun iniciando() {
        btnConhece = findViewById(R.id.btn_conheca)
    }
}