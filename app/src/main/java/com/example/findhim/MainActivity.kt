package com.example.findhim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startBtn = findViewById<Button>(R.id.startButton)
        val helpBtn = findViewById<Button>(R.id.helpButton)
        val exitBtn = findViewById<Button>(R.id.exitButton)
        val stopButton = findViewById<ImageView>(R.id.audiooff)
        stopButton.setOnClickListener {
            MusicPlayer.stop()
            Toast.makeText(this, "Music Stopped", Toast.LENGTH_SHORT).show()

        }
        val onButton = findViewById<ImageView>(R.id.audioon)
        onButton.setOnClickListener{
            MusicPlayer.start(this,R.raw.background_song)
            Toast.makeText(this, "Music On", Toast.LENGTH_SHORT).show()

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