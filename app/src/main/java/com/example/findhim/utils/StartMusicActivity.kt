package com.example.findhim.utils

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.findhim.R

open class StartMusicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!MusicPlayer.isPlaying()) {
            MusicPlayer.start(this, R.raw.background_song)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!isChangingConfigurations) {
            MusicPlayer.stop()
        }
    }
}
