package ru.shalkoff.vsu_lesson2_2024.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirplaneModeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
            val isAirplaneModeEnabled = intent.getBooleanExtra("state", false)
            if (isAirplaneModeEnabled) {
                // Режим полета включен
                Toast.makeText(context, "Режим полета включен", Toast.LENGTH_SHORT).show()
            } else {
                // Режим полета выключен
                Toast.makeText(context, "Режим полета выключен", Toast.LENGTH_SHORT).show()
            }
        }
    }
}