package com.example.asincronizedsounds_alejandrobaldeone

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private val mediaPlayers = mutableListOf<MediaPlayer>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1: Button = findViewById(R.id.btnSound1)
        val btn2: Button = findViewById(R.id.btnSound2)
        val btn3: Button = findViewById(R.id.btnSound3)

        btn1.setOnClickListener { playSound(R.raw.metal_pipe) }
        btn2.setOnClickListener { playSound(R.raw.fah_sound) }
        btn3.setOnClickListener { playSound(R.raw.indian_sound) }
    }

    private fun playSound(soundResId: Int) {

        val mediaPlayer = MediaPlayer.create(this, soundResId)

        mediaPlayers.add(mediaPlayer)

        mediaPlayer.setOnCompletionListener { mp ->
            mp.release()
            mediaPlayers.remove(mp)
        }

        mediaPlayer.start()
    }

    override fun onPause() {
        super.onPause()
        releaseAllPlayers()
    }

    override fun onDestroy() {
        releaseAllPlayers()
        super.onDestroy()
    }

    private fun releaseAllPlayers() {
        for (player in mediaPlayers.toList()) {
            if (!player.isPlaying) {
                player.release()
            } else {
                player.stop()
                player.release()
            }
        }
        mediaPlayers.clear()
    }
}
