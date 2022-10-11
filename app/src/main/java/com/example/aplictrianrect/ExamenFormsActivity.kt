package com.example.aplictrianrect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

class ExamenFormsActivity : AppCompatActivity() {

    lateinit var etNameField: EditText
    lateinit var etIdField: EditText
    lateinit var etGroupField: EditText
    lateinit var tvTestWarning: TextView
    lateinit var ibBackFromTestButton: ImageButton
    lateinit var btnStartTest: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_examen_forms)

        // Asignación de variables con los componentes de la UI
        etNameField = findViewById(R.id.etNameFieldTest)
        etIdField = findViewById(R.id.etIdFieldTest)
        etGroupField = findViewById(R.id.etGroupFieldTest)
        tvTestWarning = findViewById(R.id.tvTestWarning)
        ibBackFromTestButton = findViewById(R.id.ibBackFromTestButton)
        btnStartTest = findViewById(R.id.btnStartTest)

        // Inicialización
        tvTestWarning.visibility = View.GONE

        // On click listeners
        btnStartTest.setOnClickListener { startTest() }
        ibBackFromTestButton.setOnClickListener { backOnClick() }

    }

    fun checkFields(): Boolean {
        if(etNameField.text.toString().isEmpty()){
            return true
        }
        if(etIdField.text.toString().isEmpty()){
            return true
        }
        if(etGroupField.text.toString().isEmpty()){
            return true
        }
        return false
    }

    fun startTest(){
        if(checkFields()){
            val intent = Intent(this, PreguntaExamenActivity::class.java)
            startActivity(intent)
        }
        else{
            tvTestWarning.visibility = View.VISIBLE
        }
    }

    fun backOnClick(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}