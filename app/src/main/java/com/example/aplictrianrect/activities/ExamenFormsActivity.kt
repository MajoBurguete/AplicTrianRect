package com.example.aplictrianrect.activities

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.aplictrianrect.R
import com.example.aplictrianrect.models.AppUtility

class ExamenFormsActivity : AppCompatActivity() {

    lateinit var etNameField: EditText
    lateinit var etIdField: EditText
    lateinit var etGroupField: EditText
    lateinit var tvTestWarning: TextView
    lateinit var ibBackFromTestButton: ImageButton
    lateinit var btnStartTest: Button
    lateinit var clQuickTestForm: ConstraintLayout

    private val appUtilityInstance = AppUtility()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_examen_forms)

        // Asignación de variables con los componentes de la UI
        etNameField = findViewById(R.id.etNameFieldTest)
        etIdField = findViewById(R.id.etIdFieldTest)
        etGroupField = findViewById(R.id.etGroupFieldTest)
        tvTestWarning = findViewById(R.id.tvTestWarning)
        ibBackFromTestButton = findViewById(R.id.ibBackFromTestForm)
        btnStartTest = findViewById(R.id.btnStartTest)
        clQuickTestForm = findViewById(R.id.clQuickTestForm)

        // Inicialización
        tvTestWarning.visibility = View.GONE

        // On click listeners
        btnStartTest.setOnClickListener { startTest() }
        ibBackFromTestButton.setOnClickListener { backOnClick() }

        clQuickTestForm.setOnClickListener{
            etNameField.clearFocus()
            etIdField.clearFocus()
            etGroupField.clearFocus()
        }

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

    fun startTest(){
        if(checkFields()){
            val intent = Intent(this, PreguntaExamenActivity::class.java)

            intent.putExtra("nameTest", etNameField.text.toString())
            intent.putExtra("idTest", etIdField.text.toString())
            intent.putExtra("groupTest", etGroupField.text.toString())

            startActivity(intent)
        }
        else{
            tvTestWarning.visibility = View.VISIBLE

            var set = AnimatorSet()
            set.playTogether(
                ObjectAnimator.ofFloat(tvTestWarning, "rotation", 2.5f)
                    .setDuration(200))
            set.start()

            var set2 = AnimatorSet()
            set2.playTogether(
                ObjectAnimator.ofFloat(tvTestWarning, "rotation", -2.5f)
                    .setDuration(200))
            set2.startDelay = 100
            set2.start()

            var set3 = AnimatorSet()
            set3.playTogether(
                ObjectAnimator.ofFloat(tvTestWarning, "rotation", 0f)
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