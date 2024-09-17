package com.example.meuportifolio

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.meuportifolio.databinding.ActivityMainBinding
import com.example.meuportifolio.databinding.ActivityPrincipalBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Principal : AppCompatActivity() {
    private lateinit var binding: ActivityPrincipalBinding
    private val bancoDados = FirebaseFirestore.getInstance()
    private val autenticacao = FirebaseAuth.getInstance()
    lateinit var viewNome: TextView
    lateinit var btnVoltar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        iniciando()
        btnVoltar.setOnClickListener {
            autenticacao.signOut()
            finish()
            startActivity(Intent(this, TelaCadastro::class.java))
        }
        binding.imgGit.setOnClickListener {
            openGit("https://github.com/gabbz17")
        }
        binding.imgGmail.setOnClickListener {
            openGmail("mailto:gcoutinho470@gmail.com")
        }
        binding.imgLinke.setOnClickListener {
            openLinke("https://www.linkedin.com/in/gabriel-coutinho-8245212b6/")
        }
        binding.imgInsta.setOnClickListener {
            openInsta("https://www.instagram.com/gabbz_melo/")
        }
        binding.viewGit.setOnClickListener {
            abrirGit("https://github.com/gabbz17")
        }
        binding.viewLinke.setOnClickListener {
            abrirLinke("https://www.linkedin.com/in/gabriel-coutinho-8245212b6/")
        }
        binding.viewInsta.setOnClickListener {
            abrirInsta("https://www.instagram.com/gabbz_melo/")
        }
        binding.viewGmail.setOnClickListener {
            abrirGmail("mailto:gcoutinho470@gmail.com")
        }
        binding.btnCertificacoes.setOnClickListener {
            startActivity(Intent(this, Tela_certificacoes::class.java))
        }
        binding.viewJava.setOnClickListener {
            startActivity(Intent(this, Tela_java::class.java))
        }
        binding.btnExperiencia.setOnClickListener {
            startActivity(Intent(this, Tela_experiencia::class.java))
        }
    }

    private fun abrirGmail(link: String) {
        val uri = Uri.parse(link)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    private fun abrirInsta(link: String) {
        val uri = Uri.parse(link)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    private fun abrirLinke(link: String) {
        val uri = Uri.parse(link)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    private fun abrirGit(link: String) {
        val uri = Uri.parse(link)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    private fun openInsta(link: String) {
        val uri = Uri.parse(link)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    private fun openLinke(link: String) {
        val uri = Uri.parse(link)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    private fun openGmail(link: String) {
        val uri = Uri.parse(link)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    private fun openGit(link: String) {
        val uri = Uri.parse(link)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    private fun recuperandoDados() {
        val id = autenticacao.currentUser?.uid

        if (id != null){
            bancoDados.collection("InfoUsers").document(id).get()
                .addOnSuccessListener { documentSnapshot ->
                    val dadoUser = documentSnapshot.data
                    if (dadoUser != null){
                        val nome = dadoUser["Nome"] as String
                        viewNome.setText(nome)
                    }
                }
        }
    }

    override fun onStart() {
        super.onStart()
        recuperandoDados()
    }

    private fun iniciando() {
        btnVoltar = findViewById(R.id.btn_voltar)
        viewNome = findViewById(R.id.view_nome)
    }
}