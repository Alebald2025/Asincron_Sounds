package com.example.asincronizedsounds_alejandrobaldeone

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    // Referencias a los media player
    private var mp1: MediaPlayer? = null
    private var mp2: MediaPlayer? = null
    private var mp3: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1: Button = findViewById(R.id.btnSound1)
        val btn2: Button = findViewById(R.id.btnSound2)
        val btn3: Button = findViewById(R.id.btnSound3)

        btn1.setOnClickListener {
            playSound(1)
        }

        btn2.setOnClickListener {
            playSound(2)
        }

        btn3.setOnClickListener {
            playSound(3)
        }
    }

    private fun playSound(soundId: Int) {
        val resId = when (soundId) {
            1 -> R.raw.metal_pipe
            2 -> R.raw.fah_sound
            3 -> R.raw.indian_sound
            else -> return
        }

        // Liberamos el reproductor anterior si existe
        releasePlayer(soundId)

        // Creamos nuevo MediaPlayer
        val mediaPlayer = MediaPlayer.create(this, resId)

        // Guardamos la referencia según el botón
        when (soundId) {
            1 -> mp1 = mediaPlayer
            2 -> mp2 = mediaPlayer
            3 -> mp3 = mediaPlayer
        }

        mediaPlayer.setOnCompletionListener { mp ->
            mp.release()
            clearReference(soundId)
        }

        mediaPlayer.start()
    }

    // Liberar reproductor específico
    private fun releasePlayer(soundId: Int) {
        when (soundId) {
            1 -> {
                mp1?.release()
                mp1 = null
            }
            2 -> {
                mp2?.release()
                mp2 = null
            }
            3 -> {
                mp3?.release()
                mp3 = null
            }
        }
    }

    private fun clearReference(soundId: Int) {
        when (soundId) {
            1 -> mp1 = null
            2 -> mp2 = null
            3 -> mp3 = null
        }
    }

    // CONTROL DEL CICLO DE VIDA

    override fun onPause() {
        super.onPause()
        pauseAllSounds()           // Pausa los sonidos al salir de la app
    }

    override fun onDestroy() {
        releaseAllPlayers()        // Libera completamente los MediaPlayers
        super.onDestroy()
    }

    private fun pauseAllSounds() {
        mp1?.pause()
        mp2?.pause()
        mp3?.pause()
    }

    private fun releaseAllPlayers() {
        mp1?.release()
        mp2?.release()
        mp3?.release()

        mp1 = null
        mp2 = null
        mp3 = null
    }
}
}