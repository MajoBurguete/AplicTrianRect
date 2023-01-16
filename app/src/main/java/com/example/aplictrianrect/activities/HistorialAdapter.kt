package com.example.aplictrianrect.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplictrianrect.R
import com.example.aplictrianrect.models.Historial

class HistorialAdapter (private val mHistorial: List<Historial>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class PracticeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val tvNameRecord = itemView.findViewById<TextView>(R.id.tvNamePraceRecord)
        val tvIdRecord = itemView.findViewById<TextView>(R.id.tvIdPracticeRecord)
        val tvGroupRecord = itemView.findViewById<TextView>(R.id.tvGroupPracticeRecord)
        val tvTimeRecord = itemView.findViewById<TextView>(R.id.tvTimePracticeRecord)
        val tvDateRecord = itemView.findViewById<TextView>(R.id.tvDateTestRecord)
        val tvHourRecord = itemView.findViewById<TextView>(R.id.tvHourPracticeRecord)
        val tvTypeRecord = itemView.findViewById<TextView>(R.id.tvPracticeTypePracticeRecord)
        val tvAciertosRecord = itemView.findViewById<TextView>(R.id.tvAciertosPracticeRecord)
    }

    inner class TestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val tvNameRecord = itemView.findViewById<TextView>(R.id.tvNameTestRecord)
        val tvIdRecord = itemView.findViewById<TextView>(R.id.tvIdTestRecord)
        val tvGroupRecord = itemView.findViewById<TextView>(R.id.tvGroupTestRecord)
        val tvTimeRecord = itemView.findViewById<TextView>(R.id.tvTimeTestRecord)
        val tvDateRecord = itemView.findViewById<TextView>(R.id.tvDateTestRecord)
        val tvHourRecord = itemView.findViewById<TextView>(R.id.tvHourTestRecord)
        val tvTypeRecord = itemView.findViewById<TextView>(R.id.tvTestTypeTestRecord)
        val tvAciertosRecord = itemView.findViewById<TextView>(R.id.tvAciertosTestRecord)
    }

    override fun getItemViewType(position: Int): Int {
        if( mHistorial.get(position).tipoActividad.equals("quiz")){
            return 1 //Examen rapido
        }
        return 0 //Practica
    }

    // ... constructor and member variables
    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        if(viewType == 1){
            val testView = inflater.inflate(R.layout.record_test_item, parent, false)
            // Return a new holder instance
            return TestViewHolder(testView)
        }
        val practicetView = inflater.inflate(R.layout.record_practice_item, parent, false)
        // Return a new holder instance
        return PracticeViewHolder(practicetView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        // Get the data model based on position
        val historial: Historial = mHistorial.get(position)
        val isQuizOrPractice = historial.tipoActividad == "quiz"
        var quizHolder:TestViewHolder
        var practiceHolder: PracticeViewHolder

        var tvNameRecord: TextView
        var tvIdRecord: TextView
        var tvGroupRecord: TextView
        var tvTimeRecord: TextView
        var tvDateRecord: TextView
        var tvHourRecord: TextView
        var tvTypeRecord: TextView
        var tvAciertosRecord: TextView

        if(isQuizOrPractice) {
            quizHolder = viewHolder as TestViewHolder

            tvNameRecord = quizHolder.tvNameRecord
            tvIdRecord = quizHolder.tvIdRecord
            tvGroupRecord = quizHolder.tvGroupRecord
            tvTimeRecord = quizHolder.tvTimeRecord
            tvDateRecord = quizHolder.tvDateRecord
            tvHourRecord = quizHolder.tvHourRecord
            tvTypeRecord = quizHolder.tvTypeRecord
            tvAciertosRecord = quizHolder.tvAciertosRecord

            tvTypeRecord.text = "Examen Rápido"
        } else {
             practiceHolder = viewHolder as PracticeViewHolder

            tvNameRecord = practiceHolder.tvNameRecord
            tvIdRecord = practiceHolder.tvIdRecord
             tvGroupRecord = practiceHolder.tvGroupRecord
            tvTimeRecord = practiceHolder.tvTimeRecord
            tvDateRecord = practiceHolder.tvDateRecord
            tvHourRecord = practiceHolder.tvHourRecord
            tvTypeRecord = practiceHolder.tvTypeRecord
            tvAciertosRecord = practiceHolder.tvAciertosRecord

            tvTypeRecord.text = "Práctica"
        }
        // Set item views based on your views and data model

        tvNameRecord.text = historial.nombre
        tvIdRecord.text = historial.ident
        tvGroupRecord.text = "Grupo ${historial.grupo}"
        tvTimeRecord.text = "Tiempo ${historial.tiempoCronometro}"
        tvDateRecord.text = historial.fecha
        tvHourRecord.text = historial.hora
        tvAciertosRecord.text = "${historial.puntos}/${historial.cantEjercicios}"
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return mHistorial.size
    }

}