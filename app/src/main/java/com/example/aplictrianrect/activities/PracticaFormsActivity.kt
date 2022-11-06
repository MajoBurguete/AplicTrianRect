package com.example.aplictrianrect.activities

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.aplictrianrect.R
import com.example.aplictrianrect.models.AppUtility

class PracticaFormsActivity : AppCompatActivity() {

    lateinit var etNameField: EditText
    lateinit var etIdField: EditText
    lateinit var etGroupField: EditText
    lateinit var tvPracticeWarning: TextView
    lateinit var ibBackButton: ImageButton
    lateinit var btnStartPractice: Button
    lateinit var svPracticeForm: ScrollView

    private val appUtilityInstance = AppUtility()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practica_forms)

        // Asignación de variables con los componentes de la UI

        etNameField = findViewById(R.id.etNameField)
        etIdField = findViewById(R.id.etIdField)
        etGroupField = findViewById(R.id.etGroupField)
        tvPracticeWarning = findViewById(R.id.tvPracticeWarning)
        ibBackButton = findViewById(R.id.ibBackButton)
        btnStartPractice = findViewById(R.id.btnStartPractice)
        svPracticeForm = findViewById(R.id.svPracticeForm)

        // Inicialización
        tvPracticeWarning.visibility = View.GONE

        // Listeners
        ibBackButton.setOnClickListener { backOnClick() }
        btnStartPractice.setOnClickListener { startPractice() }

        etNameField.setOnFocusChangeListener { v, hasFocus ->
            if(!hasFocus) {
                appUtilityInstance.hideKeyboard(v, this)
            }
        }

        etIdField.setOnFocusChangeListener { v, hasFocus ->
            if(!hasFocus) {
                appUtilityInstance.hideKeyboard(v, this)
            }
        }

        etGroupField.setOnFocusChangeListener { v, hasFocus ->
            if(!hasFocus) {
                appUtilityInstance.hideKeyboard(v, this)
            }
        }



    }

    fun checkFields(): Boolean {
        if(etNameField.text.toString().isEmpty()){
            return false
        }
        if(etIdField.text.toString().isEmpty()){
            return false
        }
        if(etGroupField.text.toString().isEmpty()){
            return false
        }
        return true
    }

    fun startPractice(){
        if(checkFields()){
            val intent = Intent(this, PreguntaPracticaActivity::class.java)

            intent.putExtra("namePractice", etNameField.text.toString())
            intent.putExtra("idPractice", etIdField.text.toString())
            intent.putExtra("groupPractice", etGroupField.text.toString())

            startActivity(intent)
        }
        else{
            tvPracticeWarning.visibility = View.VISIBLE
            var set = AnimatorSet()
            set.playTogether(
                ObjectAnimator.ofFloat(tvPracticeWarning, "rotation", 2.5f)
                .setDuration(200))
            set.start()

            var set2 = AnimatorSet()
            set2.playTogether(
                ObjectAnimator.ofFloat(tvPracticeWarning, "rotation", -2.5f)
                    .setDuration(200))
            set2.startDelay = 100
            set2.start()

            var set3 = AnimatorSet()
            set3.playTogether(
                ObjectAnimator.ofFloat(tvPracticeWarning, "rotation", 0f)
                    .setDuration(200))
            set3.startDelay = 200
            set3.start()
        }
    }

    fun backOnClick(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}