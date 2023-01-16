package com.example.aplictrianrect.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplictrianrect.R
import com.example.aplictrianrect.database.AppDatabase
import com.example.aplictrianrect.models.Historial

class HistorialActivity : AppCompatActivity() {

    private val appDatabase by lazy { AppDatabase.getDatabase(this).historialDao() }
    lateinit var historial: List<Historial>

    lateinit var ibBackFromHistorial: ImageButton
    lateinit var rvRecord: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)

        // Asignación de variables con los componentes de la UI
        ibBackFromHistorial = findViewById(R.id.ibBackFromHistorial)
        rvRecord = findViewById(R.id.rvRecord)

        // Inicialización
        if(appDatabase.getAll().isNotEmpty()){
            historial = appDatabase.getAll().reversed()
        } else{
            historial = listOf()
        }

        // Create adapter passing in the sample user data
        val adapter = HistorialAdapter(historial)

        // Attach the adapter to the recyclerview to populate items
        rvRecord.adapter = adapter

        // Set layout manager to position the items
        rvRecord.layoutManager = LinearLayoutManager(this)

        // Click listeners
        ibBackFromHistorial.setOnClickListener { backOnClick() }
    }

    fun backOnClick(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}