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
import com.google.firebase.firestore.FirebaseFirestore

class InfoUser : AppCompatActivity() {
    private val bancoDados = FirebaseFirestore.getInstance()
    private val autenticacao = FirebaseAuth.getInstance()
    lateinit var btnVamos: Button
    lateinit var editNome: TextInputEditText
    lateinit var editSobre: TextInputEditText
    lateinit var inputNome: TextInputLayout
    lateinit var inputSobre: TextInputLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_info_user)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        iniciando()
        btnVamos.setOnClickListener {
            salvarDados()
        }
    }

    private fun validacao(nome: String, sobrenome: String):Boolean {
        inputNome.error = null
        inputSobre.error = null

        if (nome.isEmpty() && sobrenome.isEmpty()){
            inputNome.error = "Esqueceu de escrever seu nome"
            inputSobre.error = "E o sobrenome?"
            return false
        } else if (nome.isEmpty()){
            inputNome.error = "Esqueceu de escrever seu nome"
            return false
        } else if (sobrenome.isEmpty()){
            inputSobre.error = "E o sobrenome?"
            return false
        }

        return true
    }

    private fun salvarDados() {

        val nome = editNome.text.toString()
        val sobrenome = editSobre.text.toString()
        val uid = autenticacao.currentUser?.uid

        if (validacao(nome, sobrenome )){

            val dados = mapOf(
                        "Nome" to "$nome",
                        "Sobrenome" to "$sobrenome "
                        )

            bancoDados.collection("InfoUsers").document("$uid").set(dados)
                .addOnSuccessListener {
                    Toast.makeText(this, "Informações salvas", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, Principal::class.java))
                }.addOnFailureListener {
                    Toast.makeText(this, "Você já salvou esses nomes", Toast.LENGTH_LONG).show()
                }
        }
    }

    private fun iniciando() {
        btnVamos = findViewById(R.id.btn_vamos)
        editNome = findViewById(R.id.edit_nome)
        editSobre = findViewById(R.id.edit_sobre)
        inputNome = findViewById(R.id.input_nome)
        inputSobre = findViewById(R.id.input_sobre)
    }
}