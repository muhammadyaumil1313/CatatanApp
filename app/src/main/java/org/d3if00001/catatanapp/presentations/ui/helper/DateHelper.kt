package org.d3if00001.catatanapp.presentations.ui.helper

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {
    fun getCurrentDate() : String{
        val dateFormat = SimpleDateFormat("dd/MMM/yyyy", Locale.US)
        return dateFormat.format(Date())
    }
}