package com.example.aplictrianrect.activities

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.example.aplictrianrect.R
import com.example.aplictrianrect.database.AppDatabase
import com.example.aplictrianrect.models.AppUtility
import com.example.aplictrianrect.models.Historial
import com.example.aplictrianrect.models.Pregunta
import com.google.android.material.textfield.TextInputLayout
import java.lang.Math.abs
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class PreguntaPracticaActivity : AppCompatActivity() {

    private val appDatabase by lazy { AppDatabase.getDatabase(this).historialDao() }

    lateinit var ibBackFromPracticeQuestion: ImageButton
    lateinit var tvPracticaInfo: TextView
    lateinit var ivProblema: ImageView
    lateinit var etAnswerPracticeField: EditText
    lateinit var outlinedTextFieldAnswerPractice: TextInputLayout
    lateinit var btnCheckPractice: Button
    lateinit var btnNextQuestPractice: Button
    lateinit var clPracticeContainer: ConstraintLayout

    lateinit var preguntaActual: Pregunta
    private val appUtilityInstance = AppUtility()
    var numeroDeEjercicio = 0
    var aciertos = 0
    var ejercicioRevisado = false
    lateinit var crono: Chronometer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pregunta_practica)

        // Asignación de variables con los componentes de la UI
        ibBackFromPracticeQuestion = findViewById(R.id.ibBackFromPracticeQuestion)
        tvPracticaInfo = findViewById(R.id.tvPracticaInfo)
        ivProblema = findViewById(R.id.ivProblema)
        etAnswerPracticeField = findViewById(R.id.etAnswerPracticeField)
        outlinedTextFieldAnswerPractice = findViewById(R.id.outlinedTextFieldAnswerPractice)
        btnCheckPractice = findViewById(R.id.btnCheckPractice)
        btnNextQuestPractice = findViewById(R.id.btnNextQuestPractice)
        clPracticeContainer = findViewById(R.id.clPracticeQuestContainer)
        crono = findViewById(R.id.practiceCrono)

        // Inicialización
        preguntaActual = Pregunta("",0.0,"",0)

        // Click listeners
        ibBackFromPracticeQuestion.setOnClickListener { backOnClick() }
        btnNextQuestPractice.setOnClickListener { siguientePregunta() }
        btnCheckPractice.setOnClickListener { revisarRespuesta() }

        clPracticeContainer.setOnClickListener{
            etAnswerPracticeField.clearFocus()
        }

        etAnswerPracticeField.setOnFocusChangeListener { v, hasFocus ->
            if(!hasFocus) {
                appUtilityInstance.hideKeyboard(v, this)
            }
        }

        cargarPregunta()
        crono.start()

    }

    // genera una pregunta nueva y la muestra en la interfaz, colocando en blanco el espacio para responder
    fun cargarPregunta(){
        preguntaActual = preguntaActual.generaPreguntaRandom()
        Log.d("respuesta", preguntaActual.respuesta.toString())

        ejercicioRevisado = false
        numeroDeEjercicio += 1

        // Pone el textField, texto de la pregunta, imagen para el problema
        // Actualiza el botón y la imagen de correcto e incorrecto para un problema nuevo
        etAnswerPracticeField.setText("")
        etAnswerPracticeField.setTextColor(resources.getColor(R.color.navy_blue))
        etAnswerPracticeField.typeface = ResourcesCompat.getFont(applicationContext, R.font.montserrat_regular)
        outlinedTextFieldAnswerPractice.isEnabled = true
        etAnswerPracticeField.isEnabled = true
        btnCheckPractice.alpha = 1.0f

        tvPracticaInfo.text = preguntaActual.textoPregunta
        ivProblema.setImageResource(preguntaActual.nombreImagen)
        btnCheckPractice.text = "Revisa"
        btnCheckPractice.isEnabled = true

    }

    fun siguientePregunta(){
        etAnswerPracticeField.clearFocus()
        if(!ejercicioRevisado){
            if(!etAnswerPracticeField.text.isEmpty()){ // si tecleó la respuesta
                // se revisa el ejercicio
                // No es necesario mostrar si la respuesta es correcta o no
                // poniendo el textfield verde/rojo y la imagen del checkmark
                // porque se va a cambiar la vista para ir al siguiente
                // ejercicio.
                if(revisaRespuestaConMargenDeError(etAnswerPracticeField.text.toString().toDouble(), preguntaActual.respuesta)){
                    aciertos += 1
                }

                ejercicioRevisado = true

            }
        }
        cargarPregunta()
    }

    fun revisarRespuesta(){
        etAnswerPracticeField.clearFocus()
        outlinedTextFieldAnswerPractice.isEnabled = false
        etAnswerPracticeField.isEnabled = false
        if(btnCheckPractice.text.equals("Ver respuesta")){
            var messageDialog = "La respuesta de este ejercicio es " + preguntaActual.redondeaA2Decimales(preguntaActual.respuesta)
            createPracticeDialog(messageDialog, "Respuesta correcta")
        }
        else{
            if(!etAnswerPracticeField.text.isEmpty()){
                etAnswerPracticeField.typeface = ResourcesCompat.getFont(applicationContext, R.font.montserrat_bold)
                var respUsuario = etAnswerPracticeField.text.toString().toDouble()
                if(!ejercicioRevisado){ // si el ejercicio no se ha revisado
                    // se revisa el ejercicio
                    if(revisaRespuestaConMargenDeError(respUsuario, preguntaActual.respuesta)){
                        etAnswerPracticeField.setTextColor(resources.getColor(R.color.green))
                        btnCheckPractice.isEnabled = false
                        btnCheckPractice.alpha = 0.5f
                        aciertos += 1
                    }
                    else{
                        etAnswerPracticeField.setTextColor(resources.getColor(R.color.red))
                        // si la respuesta está equivocada, se da la opción de ver la respuesta correcta
                        btnCheckPractice.text = "Ver respuesta"
                    }

                    // se marca el ejercicio como revisado
                    ejercicioRevisado = true

                }
            }
            else{ // si se dejó el campo en blanco y se oprimió el botón de revisar
                outlinedTextFieldAnswerPractice.isEnabled = true
                etAnswerPracticeField.isEnabled = true
                createPracticeDialog("Escribe solo números con o sin punto decimal", "Datos incorrectos")
            }
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

        if (abs((usuario-correcta)) < 0.005) {
            return true
        }
        return false
    }

    fun backOnClick(){
        crono.stop()
        guardarHistorial()
        //Guardamos el historial en la base de datos

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun guardarHistorial(){
        val historial = Historial(0, intent.getStringExtra("namePractice").toString(),
            intent.getStringExtra("idPractice").toString(),
            intent.getStringExtra("groupPractice").toString(),
            appUtilityInstance.getDate(),
            appUtilityInstance.getHour(),
            aciertos,
            numeroDeEjercicio,
            "practica",
            getCronoTime())

        val dbList = appDatabase.getAll()

        if(dbList.size  == 10){
            appDatabase.delete(dbList[0])
        }

        appDatabase.insertAll(historial)

    }

    // FUNCION DE PRUEBA PARA VER CADA ELEMENTO EN LA BASE DE DATOS
    fun checkDatabase(){
        var historialList = appDatabase.getAll()

        for (hist: Historial in historialList){
            Log.d("PRACT", hist.toString())
        }
    }

    fun getCronoTime(): String {
        var timeElapsed = SystemClock.elapsedRealtime() - crono.getBase()
        val hours = (timeElapsed / 3600000).toInt()
        val minutes = (timeElapsed - hours * 3600000).toInt() / 60000
        val seconds = (timeElapsed - hours * 3600000 - minutes * 60000).toInt() / 1000

        return hours.toString() + ":" + minutes.toString() + ":" + seconds.toString()
    }

}