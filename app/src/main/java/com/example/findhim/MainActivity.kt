package com.example.findhim

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.example.findhim.utils.StartMusicActivity
import com.example.findhim.utils.MusicPlayer

class MainActivity : StartMusicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startBtn = findViewById<Button>(R.id.startButton)
        val helpBtn = findViewById<Button>(R.id.helpButton)
        val exitBtn = findViewById<Button>(R.id.exitButton)
        val historyBtn = findViewById<Button>(R.id.LogButton)

        MusicPlayer.setupMusicButton(this)

        startBtn.setOnClickListener { launchActivity(StartActivity::class.java) }
        helpBtn.setOnClickListener { launchActivity(HelpActivity::class.java) }
        exitBtn.setOnClickListener { finishAffinity() }
        historyBtn.setOnClickListener{ launchActivity(LogActivity::class.java) }

    }

    private fun launchActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }

    override fun onPause() {
        super.onPause()
        MusicPlayer.updateMusicButton(this)
    }

    override fun onResume() {
        super.onResume()
        MusicPlayer.updateMusicButton(this)
    }

}