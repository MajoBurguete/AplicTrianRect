package com.example.aplictrianrect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class PreguntaExamenActivity : AppCompatActivity() {

    lateinit var ibBackFromTestQuestion: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pregunta_examen)

        // Asignación de variables con los componentes de la UI
        ibBackFromTestQuestion = findViewById(R.id.ibBackFromPracticeQuestion)

        // Inicialización

        // Click listeners
        ibBackFromTestQuestion.setOnClickListener { backOnClick() }

    }

    fun backOnClick(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}