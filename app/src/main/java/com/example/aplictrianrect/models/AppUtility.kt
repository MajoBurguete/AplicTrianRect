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

    fun getHour(): String {
        val calendar = Calendar.getInstance(TimeZone.getDefault())

        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinutes = calendar.get(Calendar.MINUTE)


        return getHourAMorPM(currentHour, currentMinutes)
    }

    fun getHourAMorPM(hour: Int, minutes: Int): String{
        if(hour < 12){
            if(hour == 0){
                return "12:$minutes am"
            }
            if(hour == 1){
                return "01:$minutes am"
            }
            if(hour == 2){
                return "02:$minutes am"
            }
            if(hour == 3){
                return "03:$minutes am"
            }
            if(hour == 4){
                return "04:$minutes am"
            }
            if(hour == 5){
                return "05:$minutes am"
            }
            if(hour == 6){
                return "06:$minutes am"
            }
            if(hour == 7){
                return "07:$minutes am"
            }
            if(hour == 8){
                return "08:$minutes am"
            }
            if(hour == 9){
                return "09:$minutes am"
            }
            if(hour == 10){
                return "10:$minutes am"
            }
            if(hour == 11){
                return "11:$minutes am"
            }
        } else if (hour > 12){
            if(hour == 13){
                return "01:$minutes pm"
            }
            if(hour == 14){
                return "02:$minutes pm"
            }
            if(hour == 15){
                return "03:$minutes pm"
            }
            if(hour == 16){
                return "04:$minutes pm"
            }
            if(hour == 17){
                return "05:$minutes pm"
            }
            if(hour == 18){
                return "06:$minutes pm"
            }
            if(hour == 19){
                return "07:$minutes pm"
            }
            if(hour == 20){
                return "08:$minutes pm"
            }
            if(hour == 21){
                return "09:$minutes pm"
            }
            if(hour == 22){
                return "10:$minutes pm"
            }
            if(hour == 23){
                return "11:$minutes pm"
            }
        }
        return "12:$minutes pm"
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