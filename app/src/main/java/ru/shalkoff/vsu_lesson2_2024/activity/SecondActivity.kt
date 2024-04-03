package ru.shalkoff.vsu_lesson2_2024.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.shalkoff.vsu_lesson2_2024.R
import ru.shalkoff.vsu_lesson2_2024.services.BoundService

class SecondActivity : AppCompatActivity() {

    private var boundService: BoundService? = null
    private var isServiceBound = false

    private val serviceConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as BoundService.BoundServiceBinder
            boundService = binder.getService()
            isServiceBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isServiceBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initListeners()
        showUserInfo()
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, BoundService::class.java)
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        if (isServiceBound) {
            unbindService(serviceConnection)
            isServiceBound = false
        }
    }

    private fun initListeners() {
        findViewById<TextView>(R.id.second_activity_tv).setOnClickListener {
            val thirdActivityIntent = Intent(this, ThirdActivity::class.java)
            startActivity(thirdActivityIntent)
        }

        findViewById<Button>(R.id.play_music_btn).setOnClickListener {
            boundService?.playMusic()
        }
        findViewById<Button>(R.id.pause_music_btn).setOnClickListener {
            boundService?.pauseMusic()
        }
        findViewById<Button>(R.id.stop_music_btn).setOnClickListener {
            boundService?.stopMusic()
        }
    }

    private fun showUserInfo() {
        val user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableExtra("USER_INFO_KEY", User::class.java)
        } else {
            intent?.getParcelableExtra("USER_INFO_KEY")
        }
        user?.let {
            Toast.makeText(this, "Имя: ${it.name}, возраст: ${it.age}", Toast.LENGTH_LONG).show()
        }
    }
}