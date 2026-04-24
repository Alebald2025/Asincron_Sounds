package com.example.asincronizedsounds_alejandrobaldeone

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Botones
        val btn1: Button = findViewById(R.id.btnSound1)
        val btn2: Button = findViewById(R.id.btnSound2)
        val btn3: Button = findViewById(R.id.btnSound3)

        // Listeners
        btn1.setOnClickListener { playSound(R.raw.metal_pipe) }
        btn2.setOnClickListener { playSound(R.raw.fah_sound) }
        btn3.setOnClickListener { playSound(R.raw.indian_sound) }
    }

    // Función del MediaPlayer
    private fun playSound(soundResId: Int) {
        val mediaPlayer = MediaPlayer.create(this, soundResId)

        mediaPlayer.setOnCompletionListener { mp ->
            mp.release()
        }

        mediaPlayer.start()
    }
}