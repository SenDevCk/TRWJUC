package com.bih.nic.bsphcl.trwjuc.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.NumberPicker
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment


/**
 *Created by Chandan Singh on 2/13/2025.
 */
class YearPickerDialog(
    private val context: Context,
    private val initialYear: Int,
    private val onYearSelected: (Int) -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val yearPicker = NumberPicker(context)
        yearPicker.minValue = 1900  // Minimum year
        yearPicker.maxValue = 2100 // Maximum year
        yearPicker.value = initialYear // Current year

        yearPicker.setFormatter { String.format("%d", it) }

        return AlertDialog.Builder(context)
            .setTitle("Select Year")
            .setView(yearPicker)
            .setPositiveButton("OK") { _, _ ->
                val selectedYear = yearPicker.value
                onYearSelected(selectedYear)
            }
            .setNegativeButton("Cancel", null)
            .create()
    }
}
