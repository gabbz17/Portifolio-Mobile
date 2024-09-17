package com.example.meuportifolio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

class TelaCadastro : AppCompatActivity() {
    private val autenticacao = FirebaseAuth.getInstance()
    lateinit var btnAnonimo: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tela_cadastro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        iniciando()
        btnAnonimo.setOnClickListener {
            autenticacao.signInAnonymously()
                .addOnSuccessListener {
                    startActivity(Intent(this, InfoUser::class.java))
                }

        }
    }


    private fun iniciando() {
        btnAnonimo = findViewById(R.id.btn_anonimo)
    }
}