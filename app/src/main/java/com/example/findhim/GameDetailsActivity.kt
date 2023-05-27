package com.example.findhim

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.findhim.databinding.GameDetailsLayoutBinding
import com.example.findhim.persistency.Game
import com.example.findhim.utils.MusicPlayer

class GameDetailsActivity : AppCompatActivity() {

    lateinit var binding: GameDetailsLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = GameDetailsLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MusicPlayer.setupMusicButton(this)
        val bundle = intent.extras
        val game = bundle?.getParcelable<Game>("game")
        // Find the views and initialize the variables
        binding.gameTimetv.text= game?.gameTime ?: "0"
        binding.clickstv.text= game!!.clicks

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