package com.example.aplictrianrect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    lateinit var practicaButton: LinearLayout
    lateinit var examenButton: LinearLayout
    lateinit var historialButton: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Remplazar action bar con toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Asignación de variables con los componentes de la UI

        practicaButton = findViewById(R.id.linearLayoutPractica)
        examenButton = findViewById(R.id.linearLayoutExamen)
        historialButton = findViewById(R.id.linearLayoutHistorial)

        // On click listeners
        practicaButton.setOnClickListener { goToPracticeForm() }
        examenButton.setOnClickListener { goToTestForm() }
        historialButton.setOnClickListener { goToHistorial() }


    }

    private fun goToPracticeForm(){
        val intent = Intent(this, PracticaFormsActivity::class.java)
        startActivity(intent)
    }

    private fun goToTestForm(){
        val intent = Intent(this, ExamenFormsActivity::class.java)
        startActivity(intent)
    }

    private fun goToHistorial(){
        val intent = Intent(this, HistorialActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
}