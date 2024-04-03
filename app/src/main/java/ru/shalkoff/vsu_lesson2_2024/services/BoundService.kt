package ru.shalkoff.vsu_lesson2_2024.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.widget.Toast

class BoundService : Service() {

    private val binder = BoundServiceBinder()

    inner class BoundServiceBinder : Binder() {

        fun getService(): BoundService {
            return this@BoundService
        }
    }

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, "BoundService запущен", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "BoundService остановлен", Toast.LENGTH_SHORT).show()
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    fun playMusic() {
        Toast.makeText(this, "Музыка начала воспроизводиться", Toast.LENGTH_SHORT).show()
    }

    fun pauseMusic() {
        Toast.makeText(this, "Музыка приостановлена", Toast.LENGTH_SHORT).show()
    }

    fun stopMusic() {
        Toast.makeText(this, "Музыка остановлена", Toast.LENGTH_SHORT).show()
    }
}
