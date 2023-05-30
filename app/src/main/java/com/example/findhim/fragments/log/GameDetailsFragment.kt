package com.example.findhim.fragments.log

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.findhim.databinding.FragmentGameDetailsBinding
import com.example.findhim.persistency.Game
import com.example.findhim.utils.MusicPlayer

class GameDetailsFragment : Fragment() {

    private lateinit var binding: FragmentGameDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MusicPlayer.setupMusicButton(requireActivity() as AppCompatActivity)
        val bundle = arguments
        val game = bundle?.getParcelable<Game>("game")

        // Find the views and initialize the variables
        binding.gameTimetv.text = game?.gameTime ?: "0"
        binding.clickstv.text = game!!.clicks
        Log.e("af", "$game.gameTime")
        binding.backButton.setOnClickListener { goBack() }
    }

    private fun goBack() {
        requireActivity().finish()
    }

    override fun onPause() {
        super.onPause()
        MusicPlayer.updateMusicButton(requireActivity() as AppCompatActivity)
    }

    override fun onResume() {
        super.onResume()
        MusicPlayer.updateMusicButton(requireActivity() as AppCompatActivity)
    }
}
