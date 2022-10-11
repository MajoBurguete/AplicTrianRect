package com.example.aplictrianrect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class PreguntaPracticaActivity : AppCompatActivity() {

    lateinit var ibBackFromPracticeQuestion: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pregunta_practica)

        // Asignación de variables con los componentes de la UI
        ibBackFromPracticeQuestion = findViewById(R.id.ibBackFromPracticeQuestion)

        // Inicialización

        // Click listeners
        ibBackFromPracticeQuestion.setOnClickListener { backOnClick() }

    }

    fun backOnClick(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}