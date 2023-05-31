package com.example.findhim

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.example.findhim.databinding.ActivityMainBinding
import com.example.findhim.utils.StartMusicActivity
import com.example.findhim.utils.MusicPlayer

class MainActivity : StartMusicActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val startBtn = binding.startButton
        val helpBtn = binding.helpButton
        val exitBtn = binding.exitButton
        val historyBtn = binding.LogButton
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