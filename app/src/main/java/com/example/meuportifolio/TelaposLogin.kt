package com.example.meuportifolio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TelaposLogin : AppCompatActivity() {
    lateinit var btnEntrar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_telapos_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        iniciando()
        btnEntrar.setOnClickListener {
            startActivity(Intent(this, Principal::class.java))
        }
    }

    private fun iniciando() {
        btnEntrar = findViewById(R.id.btn_entrar)
    }
}