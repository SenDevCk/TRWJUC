package com.bih.nic.bsphcl.trwjuc.utils

import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.widget.Button
import androidx.appcompat.app.AlertDialog
/**
 *Created by Chandan Singh on 2/20/2025.
 */



fun showPermissionExplanationUI(context: Context) {
    val dialog = AlertDialog.Builder(context)
        .setTitle("Permission Required")
        .setMessage("This feature requires access to your device's location to function properly. If you decline, the location-based features of the app will be disabled.")
        .setPositiveButton("Grant Permission") { _, _ ->
            // Navigate to the app settings or request permission if needed
            // If you're targeting location permissions, you might want to ask the user to enable permissions in the app settings
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            intent.data = android.net.Uri.parse("package:${context.packageName}")
            context.startActivity(intent)
        }
        .setNegativeButton("No Thanks") { dialog, _ ->
            dialog.dismiss()
            // Continue using the app with limited functionality
        }
        .create()

    dialog.show()
}
