package com.example.aplictrianrect.models

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.util.*

class AppUtility {

    fun hideKeyboard(view: View, activity: Activity){
        val imm: InputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
    }

    fun getDate(): String {
        val calendar = Calendar.getInstance(TimeZone.getDefault())

        val currentYear = calendar[Calendar.YEAR]
        val currentMonth = calendar[Calendar.MONTH]
        val currentDay = calendar[Calendar.DAY_OF_MONTH]

        return "${getDay(currentDay)} ${getMonth(currentMonth)}. $currentYear"
    }

    fun getDay(day: Int): String {
        if(day < 10){
            return "0$day"
        }
        return day.toString()
    }

    fun getMonth(month: Int): String {
        if(month == 0){
            return "ene"
        }
        if(month == 1){
            return "feb"
        }
        if(month == 2){
            return "mar"
        }
        if(month == 3){
            return "abr"
        }
        if(month == 4){
            return "may"
        }
        if(month == 5){
            return "jun"
        }
        if(month == 6){
            return "jul"
        }
        if(month == 7){
            return "ago"
        }
        if(month == 8){
            return "sep"
        }
        if(month == 9){
            return "oct"
        }
        if(month == 10){
            return "nov"
        }
        return "dic"
    }

}