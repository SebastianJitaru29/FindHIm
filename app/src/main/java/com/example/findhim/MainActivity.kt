package com.example.findhim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startBtn = findViewById<Button>(R.id.startButton)
        val helpBtn = findViewById<Button>(R.id.helpButton)
        val exitBtn = findViewById<Button>(R.id.exitButton)

        startBtn.setOnClickListener { launchActivity(StartActivity::class.java) }
        helpBtn.setOnClickListener { launchActivity(HelpActivity::class.java) }
        exitBtn.setOnClickListener { finishAffinity() }

    }
    private fun launchActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }


}