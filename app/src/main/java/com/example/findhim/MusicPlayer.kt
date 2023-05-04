package com.example.findhim

import android.content.Context
import android.media.MediaPlayer
import androidx.annotation.RawRes

object MusicPlayer {
    private var mediaPlayer: MediaPlayer? = null
    private var isPlaying = false

    fun start(context: Context, @RawRes musicId: Int) {
        if (isPlaying) {
            return
        }

        mediaPlayer = MediaPlayer.create(context, musicId)
        mediaPlayer?.isLooping = true
        mediaPlayer?.start()
        isPlaying = true
    }

    fun stop() {
        mediaPlayer?.pause()
        mediaPlayer?.release()
        mediaPlayer = null
        isPlaying = false
    }

    fun isPlaying(): Boolean {
        return isPlaying
    }
}
