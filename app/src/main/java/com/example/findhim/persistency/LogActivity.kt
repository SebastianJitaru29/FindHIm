package com.example.findhim.persistency

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.findhim.R
import com.example.findhim.databinding.LogLayoutBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class LogActivity : AppCompatActivity(){
    private val gameViewModel: GameViewModel by viewModels { GameViewModelFactory((application as GameApplication).repository)
    }
    private lateinit var binding: LogLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LogLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = GameListAdapter()
        binding.rv?.adapter = adapter
        binding.rv?.layoutManager = LinearLayoutManager(this)


        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        gameViewModel.allGames.observe(this) { games ->
            // Update the cached copy of the words in the adapter.
            games.let { adapter.submitList(it) }
        }
    }

}