package com.example.aplictrianrect.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction
import com.example.aplictrianrect.R
import com.example.aplictrianrect.fragments.CreditosFragment


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

    private fun openCreditsFragment(){
        Log.d("MIAU", "openCreditsFragment: ")
        val ft = supportFragmentManager.beginTransaction()

        ft.replace(R.id.frameLayoutCreditos, CreditosFragment()).commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar items
        when (item.itemId) {
            R.id.creditos_btn -> {
                openCreditsFragment()
                return true
            }
            else ->
                return super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
}