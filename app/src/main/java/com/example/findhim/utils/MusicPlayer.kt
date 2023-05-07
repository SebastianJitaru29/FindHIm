package com.example.findhim.utils

import android.content.Context
import android.media.MediaPlayer
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RawRes
import androidx.appcompat.app.AppCompatActivity
import com.example.findhim.R

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

    fun setupMusicButton(activity: AppCompatActivity) {
        val musicButton = activity.findViewById<ImageView>(R.id.music)
        updateMusicButton(activity)
        musicButton.setOnClickListener {
            if (isPlaying()) {
                musicButton.setBackgroundResource(R.drawable.audio_off)
                stop()
                Toast.makeText(activity, activity.getString(R.string.music_off), Toast.LENGTH_SHORT)
                    .show()
            } else {
                musicButton.setBackgroundResource(R.drawable.audio_on)
                start(activity, R.raw.background_song)
                Toast.makeText(activity, activity.getString(R.string.music_on), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun updateMusicButton(activity: AppCompatActivity) {
        val musicButton = activity.findViewById<ImageView>(R.id.music)
        if (isPlaying())
            musicButton.setBackgroundResource(R.drawable.audio_on)
        else
            musicButton.setBackgroundResource(R.drawable.audio_off)
    }
}
