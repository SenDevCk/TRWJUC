package com.bih.nic.bsphcl.trwjuc.utils

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object DateUtil {

    private const val DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss"

    /**
     * Formats a given Date object to a string.
     * Works for all API levels.
     * val formattedDate = DateUtil.getCurrentDate("dd/MM/yyyy HH:mm")
     * println("Formatted Date: $formattedDate") // Output: 26/02/2025 14:45
     *
     */
    fun formatDate(date: Date, format: String = DEFAULT_FORMAT): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val formatter = DateTimeFormatter.ofPattern(format, Locale.getDefault())
            date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(formatter)
        } else {
            val sdf = SimpleDateFormat(format, Locale.getDefault())
            sdf.format(date)
        }
    }

    /**
     * Parses a date string into a Date object.
     * Works for all API levels.
     * val date = Date()
     * val formattedDate = DateUtil.formatDate(date, "EEE, MMM d, yyyy")
     * println("Formatted Date: $formattedDate") // Output: Wed, Feb 26, 2025
     * val dateStr = "2025-02-26 10:30:00"
     * val parsedDate = DateUtil.parseDate(dateStr)
     * println("Parsed Date: $parsedDate") // Output: Wed Feb 26 10:30:00 GMT 2025
     */
    fun parseDate(dateStr: String, format: String = DEFAULT_FORMAT): Date? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            try {
                val formatter = DateTimeFormatter.ofPattern(format, Locale.getDefault())
                val localDateTime = LocalDateTime.parse(dateStr, formatter)
                Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())
            } catch (e: Exception) {
                null
            }
        } else {
            try {
                val sdf = SimpleDateFormat(format, Locale.getDefault())
                sdf.parse(dateStr)
            } catch (e: Exception) {
                null
            }
        }
    }

    /**
     * Converts a Date object to UTC formatted string.
     * Works for all API levels.
     * val utcDate = DateUtil.formatDateToUTC(Date())
     * println("UTC Date: $utcDate") // Output: 2025-02-26 14:45:00
     *
     */
    fun formatDateToUTC(date: Date, format: String = DEFAULT_FORMAT): String {
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        return sdf.format(date)
    }

    /**
     * Gets the current date/time as a formatted string.
     * Works for all API levels.
     */
    fun getCurrentDate(format: String = DEFAULT_FORMAT): String {
        return formatDate(Date(), format)
    }

    fun convertDateFormat(dateStr: String, fromFormat: String = "dd/MM/yyyy", toFormat: String = "MM-dd-yyyy"): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            try {
                val inputFormatter = DateTimeFormatter.ofPattern(fromFormat, Locale.getDefault())
                val outputFormatter = DateTimeFormatter.ofPattern(toFormat, Locale.getDefault())
                val date = LocalDate.parse(dateStr, inputFormatter)
                date.format(outputFormatter)
            } catch (e: Exception) {
                "Invalid Date"
            }
        } else {
            try {
                val inputFormatter = SimpleDateFormat(fromFormat, Locale.getDefault())
                val outputFormatter = SimpleDateFormat(toFormat, Locale.getDefault())
                val date = inputFormatter.parse(dateStr)
                outputFormatter.format(date ?: return "Invalid Date")
            } catch (e: Exception) {
                "Invalid Date"
            }
        }
    }
}
