package com.example.aplictrianrect.activities

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Chronometer
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.aplictrianrect.R
import com.example.aplictrianrect.database.AppDatabase
import com.example.aplictrianrect.models.Historial
import com.example.aplictrianrect.models.Pregunta
import java.util.*

class PreguntaPracticaActivity : AppCompatActivity() {

    private val appDatabase by lazy { AppDatabase.getDatabase(this).historialDao() }

    lateinit var ibBackFromPracticeQuestion: ImageButton
    lateinit var preguntaActual: Pregunta

    //Para guardar el historial de los intentos realizados por el usuario
    lateinit var arregloHistorial: Vector<Historial>
    var numeroDeEjercicio = 0
    var aciertos = 0
    var ejercicioRevisado = false
    lateinit var crono: Chronometer
    var tiempoEnSegundos = 0

    lateinit var historial: Historial


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pregunta_practica)

        // Asignación de variables con los componentes de la UI
        ibBackFromPracticeQuestion = findViewById(R.id.ibBackFromPracticeQuestion)
        crono = findViewById(R.id.practiceCrono)

        // Inicialización

        // Click listeners
        ibBackFromPracticeQuestion.setOnClickListener { backOnClick() }

        crono.start()

    }

    fun backOnClick(){
        crono.stop()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun getCronoTime(): String {
        var timeElapsed = SystemClock.elapsedRealtime() - crono.getBase()
        val hours = (timeElapsed / 3600000).toInt()
        val minutes = (timeElapsed - hours * 3600000).toInt() / 60000
        val seconds = (timeElapsed - hours * 3600000 - minutes * 60000).toInt() / 1000

        return hours.toString() + ":" + minutes.toString() + ":" + seconds.toString()
    }

    override fun onStop() {
        super.onStop()
        Log.d("PRACT", "onStop: " + getCronoTime())
    }

}