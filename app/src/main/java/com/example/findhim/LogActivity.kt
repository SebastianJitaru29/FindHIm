package com.example.findhim

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findhim.databinding.LogLayoutBinding
import com.example.findhim.persistency.GameApplication
import com.example.findhim.persistency.GameListAdapter
import com.example.findhim.persistency.GameViewModel
import com.example.findhim.persistency.GameViewModelFactory

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