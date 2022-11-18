package com.example.aplictrianrect.activities

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.example.aplictrianrect.R
import com.example.aplictrianrect.database.AppDatabase
import com.example.aplictrianrect.models.AppUtility
import com.example.aplictrianrect.models.Historial
import com.example.aplictrianrect.models.Pregunta
import com.google.android.material.textfield.TextInputLayout

class PreguntaExamenActivity : AppCompatActivity() {

    private val appDatabase by lazy { AppDatabase.getDatabase(this).historialDao() }

    lateinit var ibBackFromTestQuestion: ImageButton
    lateinit var tvCountDownTimer: TextView
    lateinit var btnFirst: Button
    lateinit var btnSecond: Button
    lateinit var btnThird: Button
    lateinit var btnFourth: Button
    lateinit var btnFifth: Button
    lateinit var tvTestInfo: TextView
    lateinit var ivProblema: ImageView
    lateinit var etAnswerTestField: EditText
    lateinit var outlinedTextFieldAnswerTest: TextInputLayout
    lateinit var btnCheckTest: Button
    lateinit var btnNextQuestTest: Button
    lateinit var clQuickTestQuest: ConstraintLayout
    lateinit var nameTest: String
    lateinit var idTest: String
    lateinit var groupTest: String
    lateinit var finalTimeS: String

    lateinit var preguntaActual: Pregunta
    private val appUtilityInstance = AppUtility()
    var numeroDeEjercicio = 0
    var aciertos = 0
    var ejercicioRevisado = false
    lateinit var timer: CountDownTimer
    var secondsRemaining = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pregunta_examen)

        // Asignación de variables con los componentes de la UI
        ibBackFromTestQuestion = findViewById(R.id.ibBackTestQuestion)
        tvTestInfo = findViewById(R.id.tvTestInfo)
        ivProblema = findViewById(R.id.ivProblemaTest)
        etAnswerTestField = findViewById(R.id.etAnswerTestField)
        outlinedTextFieldAnswerTest = findViewById(R.id.outlinedTextFieldAnswerTest)
        btnCheckTest = findViewById(R.id.btnCheckTest)
        btnNextQuestTest = findViewById(R.id.btnNextQuestTest)
        clQuickTestQuest = findViewById(R.id.clQuickTestQuest)
        btnFirst = findViewById(R.id.btnFirst)
        btnSecond = findViewById(R.id.btnSecond)
        btnThird = findViewById(R.id.btnThird)
        btnFourth = findViewById(R.id.btnFourth)
        btnFifth = findViewById(R.id.btnFifth)

        tvCountDownTimer = findViewById(R.id.tvCountDownTimer)

        // Inicialización
        preguntaActual = Pregunta("",0.0,"",0)
        nameTest = intent.getStringExtra("nameTest").toString()
        idTest = intent.getStringExtra("idTest").toString()
        groupTest = intent.getStringExtra("groupTest").toString()

        // Click listeners
        ibBackFromTestQuestion.setOnClickListener { backOnClick() }
        btnNextQuestTest.setOnClickListener { siguientePregunta() }
        btnCheckTest.setOnClickListener { revisarRespuesta() }

        clQuickTestQuest.setOnClickListener{
            etAnswerTestField.clearFocus()
        }

        etAnswerTestField.setOnFocusChangeListener { v, hasFocus ->
            if(!hasFocus) {
                appUtilityInstance.hideKeyboard(v, this)
            }
        }

        cargarPregunta()

        startTimer()

    }

    // genera una pregunta nueva y la muestra en la interfaz, colocando en blanco el espacio para responder
    fun cargarPregunta(){
        preguntaActual = preguntaActual.generaPreguntaRandom()
        Log.d("examen", preguntaActual.respuesta.toString())
        Log.d("examen", "$numeroDeEjercicio")

        ejercicioRevisado = false
        numeroDeEjercicio += 1

        // Pone el textField, texto de la pregunta, imagen para el problema
        // Actualiza el botón y la imagen de correcto e incorrecto para un problema nuevo
        etAnswerTestField.setText("")
        etAnswerTestField.setTextColor(resources.getColor(R.color.navy_blue))
        etAnswerTestField.typeface = ResourcesCompat.getFont(applicationContext, R.font.montserrat_regular)
        outlinedTextFieldAnswerTest.isEnabled = true
        etAnswerTestField.isEnabled = true
        btnCheckTest.alpha = 1.0f

        tvTestInfo.text = preguntaActual.textoPregunta
        ivProblema.setImageResource(preguntaActual.nombreImagen)
        btnCheckTest.text = "Revisa"
        btnCheckTest.isEnabled = true

        //Si el usuario se encuentra en la ultima pregunta, el texto del boton cambia
        if(numeroDeEjercicio == 5){
            btnNextQuestTest.text = "Entregar"
        }

    }


    fun siguientePregunta(){
        etAnswerTestField.clearFocus()
        if(!ejercicioRevisado){
            if(!etAnswerTestField.text.isEmpty()){ // si tecleó la respuesta
                // se revisa el ejercicio
                // No es necesario mostrar si la respuesta es correcta o no
                // poniendo el textfield verde/rojo y la imagen del checkmark
                // porque se va a cambiar la vista para ir al siguiente
                // ejercicio.
                if(revisaRespuestaConMargenDeError(etAnswerTestField.text.toString().toDouble(), preguntaActual.respuesta)){
                    colorBtnRightAnswer()
                    aciertos += 1
                } else{
                    colorBtnWrongAnswer()
                }

                ejercicioRevisado = true

            } else { // TODO: Preguntar que pasa cuando no teclean respuesta y se pasan a otra pregunta
                colorBtnWrongAnswer()
            }
        }

        if(numeroDeEjercicio == 5){
            goToResultActivity()
        } else {
            cargarPregunta()
        }
    }

    fun revisarRespuesta(){
        etAnswerTestField.clearFocus()
        outlinedTextFieldAnswerTest.isEnabled = false
        etAnswerTestField.isEnabled = false
        if(btnCheckTest.text.equals("Ver respuesta")){
            var messageDialog = "La respuesta de este ejercicio es " + preguntaActual.redondeaA2Decimales(preguntaActual.respuesta)
            createPracticeDialog(messageDialog, "Respuesta correcta")
        }
        else{
            if(!etAnswerTestField.text.isEmpty()){
                etAnswerTestField.typeface = ResourcesCompat.getFont(applicationContext, R.font.montserrat_bold)
                var respUsuario = etAnswerTestField.text.toString().toDouble()
                if(!ejercicioRevisado){ // si el ejercicio no se ha revisado
                    // se revisa el ejercicio
                    if(revisaRespuestaConMargenDeError(respUsuario, preguntaActual.respuesta)){
                        colorBtnRightAnswer()
                        etAnswerTestField.setTextColor(resources.getColor(R.color.green))
                        btnCheckTest.isEnabled = false
                        btnCheckTest.alpha = 0.5f
                        aciertos += 1
                    }
                    else{
                        colorBtnWrongAnswer()
                        etAnswerTestField.setTextColor(resources.getColor(R.color.red))
                        // si la respuesta está equivocada, se da la opción de ver la respuesta correcta
                        btnCheckTest.text = "Ver respuesta"
                    }

                    // se marca el ejercicio como revisado
                    ejercicioRevisado = true

                }
            }
            else{ // si se dejó el campo en blanco y se oprimió el botón de revisar
                outlinedTextFieldAnswerTest.isEnabled = true
                etAnswerTestField.isEnabled = true
                createPracticeDialog("Escribe solo números con o sin punto decimal", "Datos incorrectos")
            }
        }
    }

    fun colorBtnRightAnswer(){
        if(numeroDeEjercicio == 1){
            btnFirst.setBackgroundColor(resources.getColor(R.color.green))
        } else if(numeroDeEjercicio == 2){
            btnSecond.setBackgroundColor(resources.getColor(R.color.green))
        } else if(numeroDeEjercicio == 3){
            btnThird.setBackgroundColor(resources.getColor(R.color.green))
        } else if(numeroDeEjercicio == 4){
            btnFourth.setBackgroundColor(resources.getColor(R.color.green))
        } else if(numeroDeEjercicio == 5){
            btnFifth.setBackgroundColor(resources.getColor(R.color.green))
        }
    }

    fun colorBtnWrongAnswer(){
        if(numeroDeEjercicio == 1){
            btnFirst.setBackgroundColor(resources.getColor(R.color.red))
        } else if(numeroDeEjercicio == 2){
            btnSecond.setBackgroundColor(resources.getColor(R.color.red))
        } else if(numeroDeEjercicio == 3){
            btnThird.setBackgroundColor(resources.getColor(R.color.red))
        } else if(numeroDeEjercicio == 4){
            btnFourth.setBackgroundColor(resources.getColor(R.color.red))
        } else if(numeroDeEjercicio == 5){
            btnFifth.setBackgroundColor(resources.getColor(R.color.red))
        }
    }

    fun createPracticeDialog(messageDialog: String, titleDialog: String){
        val alertDialog: AlertDialog = this?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setTitle(titleDialog)
                setMessage(messageDialog)
                setPositiveButton("OK",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User clicked OK button
                    })
            }

            builder.create()
        }

        alertDialog.show()

    }

    fun revisaRespuestaConMargenDeError(usuario : Double, correcta : Double) : Boolean {

        if (Math.abs((usuario - correcta)) < 0.005) {
            return true
        }
        return false
    }

    // Iniciar el timer
    fun startTimer(){
        timer = object : CountDownTimer(601000, 1000){
            override fun onFinish() {
                goToResultActivity()
            }

            override fun onTick(millisUntilFinished: Long) {
                secondsRemaining = millisUntilFinished / 1000
                updateCountdown()
            }
        }.start()
    }

    // Funcion para actualizar el text view del timer
    fun updateCountdown(){
        val minutesUntilFinished = secondsRemaining / 60
        val secondsInMinuteUntilFinished = secondsRemaining - minutesUntilFinished * 60
        val secondsStr = secondsInMinuteUntilFinished.toString()

        tvCountDownTimer.text = "$minutesUntilFinished:${
            if(secondsStr.length == 2) secondsStr
            else "0" + secondsStr}"


    }

    //Obtiene el tiempo en el que se completo la actividad
    fun getFinalTime(): String {
        val timePassed = 600 - secondsRemaining
        val minutesPassed = timePassed / 60
        val secondsInMinuteUntilFinished = timePassed - minutesPassed * 60
        val secondsStr = secondsInMinuteUntilFinished.toString()

        finalTimeS = "$minutesPassed:${
            if(secondsStr.length == 2) secondsStr
            else "0" + secondsStr}"

        return finalTimeS
    }

    /* TODO: Hacer funcion para obtener el tiempo en el que completo la prueba, agregar el tiempo al
     objeto del historial, comprobar que se este guardando bien en la base de datos*/
    fun guardarHistorial(){
        val historial = Historial(0,
            nameTest,
            idTest,
            groupTest,
            appUtilityInstance.getDate(),
            aciertos,
            5,
            "quiz",
            getFinalTime())

        val dbList = appDatabase.getAll()

        if(dbList.size  == 10){
            appDatabase.delete(dbList[0])
        }

        appDatabase.insertAll(historial)

    }

    fun goToResultActivity(){
        timer.cancel()
        guardarHistorial()
        val intent = Intent(this@PreguntaExamenActivity, ResultadosActivity::class.java)

        intent.putExtra("nameResults", nameTest)
        intent.putExtra("idResults", idTest)
        intent.putExtra("groupResults", groupTest)
        intent.putExtra("timeResults",finalTimeS)
        intent.putExtra("dateResults", appUtilityInstance.getDate())
        intent.putExtra("hourResults", appUtilityInstance.getHour())
        intent.putExtra("aciertosResults", aciertos)

        startActivity(intent)
    }

    fun backOnClick(){
        // TODO: Si se regresan se guarda en el historial el intento?
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}