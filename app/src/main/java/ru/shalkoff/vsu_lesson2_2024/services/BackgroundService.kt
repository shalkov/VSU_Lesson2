package ru.shalkoff.vsu_lesson2_2024.services

import android.app.Service

import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class BackgroundService : Service() {

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, "BackgroundService запущен", Toast.LENGTH_SHORT).show()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Возвращает значение START_STICKY,
        // чтобы сервис был перезапущен, если его система уничтожит из-за нехватки памяти
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "BackgroundService остановлен", Toast.LENGTH_SHORT).show()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null // Мы не предоставляем привязку к этой службе
    }
}
