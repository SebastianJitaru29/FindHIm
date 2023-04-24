package com.example.findhim

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {

    private lateinit var textInput1: TextView
    private lateinit var textInput2: TextView

    companion object {
        const val MESSAGE_KEY = "message"
        const val REPETITIONS_KEY = "repetitions"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_layout)

        textInput1 = findViewById(R.id.text_input1)
        textInput2 = findViewById(R.id.text_input2)

        // Get the input values passed from the MainActivity
        val message = intent.getStringExtra(MESSAGE_KEY)
        val repetitions = intent.getIntExtra(REPETITIONS_KEY, 0)

        // Display the input values in the UI
        textInput1.text = message
        textInput2.text = repetitions.toString()
    }
}

