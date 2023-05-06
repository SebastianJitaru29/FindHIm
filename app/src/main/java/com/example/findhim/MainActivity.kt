package com.example.findhim

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startBtn = findViewById<Button>(R.id.startButton)
        val helpBtn = findViewById<Button>(R.id.helpButton)
        val exitBtn = findViewById<Button>(R.id.exitButton)

        var toast: Toast? = null

        val musicButton = findViewById<ImageView>(R.id.music)
        musicButton.setOnClickListener {
            if (MusicPlayer.isPlaying()) {
                musicButton.setBackgroundResource(R.drawable.audio_off)
                MusicPlayer.stop()
                toast?.cancel()
                toast = Toast.makeText(this, "Music Stopped", Toast.LENGTH_SHORT)
                toast?.show()
            } else {
                musicButton.setBackgroundResource(R.drawable.audio_on)
                MusicPlayer.start(this, R.raw.background_song)
                toast?.cancel()
                toast = Toast.makeText(this, "Music On", Toast.LENGTH_SHORT)
                toast?.show()
            }
        }

        startBtn.setOnClickListener { launchActivity(StartActivity::class.java) }
        helpBtn.setOnClickListener { launchActivity(HelpActivity::class.java) }
        exitBtn.setOnClickListener { finishAffinity() }

    }

    private fun launchActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }


}