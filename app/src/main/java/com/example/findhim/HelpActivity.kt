package com.example.findhim

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HelpActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.help_layout)
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
    }

    fun goBack(view: View) {
        finish()
    }

}


