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
    }

    fun goBack(view: View) {
        finish()
    }

}


