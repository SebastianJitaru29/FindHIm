package com.example.findhim

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.findhim.utils.MusicPlayer

class HelpActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.help_layout)
        MusicPlayer.setupMusicButton(this)

    }

    fun goBack(view: View) {
        finish()
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


