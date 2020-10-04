package com.example.workshopretrofit_16072020.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*


private const val LANDING_DATE = "2012-08-06"

class DateConverter {

    fun testingDate(date: String):Int {
        val cal1 =  GregorianCalendar()
        val cal2 =  GregorianCalendar()
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val date1 = sdf.parse(LANDING_DATE)
        val date2 = sdf.parse(date)
        cal1.time = date1
        cal2.time = date2
        return daysBetween(cal1.time,cal2.time)
    }

    private fun daysBetween(d1: Date, d2: Date): Int {
        return ((d2.time - d1.time) / (1000 * 60 * 60 * 24)).toInt()
    }

    fun convertDate(date: Long): String{
        val formatter = SimpleDateFormat("yyyy-M-d", Locale.getDefault())
        return formatter.format(date)
    }

    fun convertDate(date: String): String{
        val formatter = SimpleDateFormat("yyyy-M-d", Locale.getDefault())
        val another = formatter.parse(date)
        return formatter.format(another!!)
    }

    fun addOneDay(date: String):String {
        val currentDay = LocalDate.parse(date)
        val nextDay = currentDay.plus(1, ChronoUnit.DAYS).toString()
        return convertDate(nextDay)
    }

}