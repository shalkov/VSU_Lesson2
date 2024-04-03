package ru.shalkoff.vsu_lesson2_2024.receiver

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

//TODO 4
// Почему не работает этот ресивер?
// Сделайте так, чтобы он работал
class BluetoothStateReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == BluetoothAdapter.ACTION_STATE_CHANGED) {
            val state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)
            when (state) {
                BluetoothAdapter.STATE_OFF -> {
                    Toast.makeText(
                        context,
                        "Bluetooth OFF",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                BluetoothAdapter.STATE_ON -> {
                    Toast.makeText(
                        context,
                        "Bluetooth ON",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
