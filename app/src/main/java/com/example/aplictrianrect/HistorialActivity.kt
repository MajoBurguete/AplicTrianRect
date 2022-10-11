package com.example.aplictrianrect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class HistorialActivity : AppCompatActivity() {

    lateinit var ibBackFromHistorial: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)

        // Asignación de variables con los componentes de la UI
        ibBackFromHistorial = findViewById(R.id.ibBackFromHistorial)

        // Inicialización

        // Click listeners
        ibBackFromHistorial.setOnClickListener { backOnClick() }
    }

    fun backOnClick(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}