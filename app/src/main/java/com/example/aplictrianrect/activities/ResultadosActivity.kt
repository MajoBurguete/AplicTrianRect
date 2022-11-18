package com.example.aplictrianrect.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.aplictrianrect.R

class ResultadosActivity : AppCompatActivity() {

    lateinit var tvNameResults: TextView
    lateinit var tvIdResults: TextView
    lateinit var tvGroupResults: TextView
    lateinit var tvTimeResults: TextView
    lateinit var tvDateResults: TextView
    lateinit var tvHourResults: TextView
    lateinit var tvAciertosResults: TextView
    lateinit var btnReadyResults: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultados)

        // Asignación de variables con los componentes de la UI
        tvNameResults = findViewById(R.id.tvNameResults)
        tvIdResults = findViewById(R.id.tvIdResults)
        tvGroupResults = findViewById(R.id.tvGroupResults)
        tvTimeResults = findViewById(R.id.tvTimeResults)
        tvDateResults = findViewById(R.id.tvDateResults)
        tvHourResults = findViewById(R.id.tvHourResults)
        tvAciertosResults = findViewById(R.id.tvAciertosResults)
        btnReadyResults = findViewById(R.id.btnReadyResults)

        // Inicialización
        tvNameResults.text = intent.getStringExtra("nameResults")
        tvIdResults.text = intent.getStringExtra("idResults")
        tvGroupResults.text = "Grupo " + intent.getStringExtra("groupResults")
        tvTimeResults.text = "Tiempo " + intent.getStringExtra("timeResults")
        tvDateResults.text = intent.getStringExtra("dateResults")
        tvHourResults.text = intent.getStringExtra("hourResults")

        val aciertos = intent.getIntExtra("aciertosResults",0)
        tvAciertosResults.text = "$aciertos/5"

        // Click listeners
        btnReadyResults.setOnClickListener { backToMain() }
    }

    fun backToMain(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}