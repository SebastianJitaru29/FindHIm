package com.example.findhim

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class StartActivity: AppCompatActivity() {
    private lateinit var PlayerName:EditText
    private lateinit var NumberOfTrys:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_layout)
        PlayerName = findViewById(R.id.firstInputEditText)
        NumberOfTrys = findViewById(R.id.secondInputEditText)
        val startGameButton = findViewById<Button>(R.id.saveButton)
        startGameButton.setOnClickListener { startGame() }
    }

    private fun startGame() {
        val message = PlayerName.text.toString()
        val repetitions = NumberOfTrys.text.toString().toIntOrNull() ?: 0

        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra(GameActivity.MESSAGE_KEY, message)
        intent.putExtra(GameActivity.REPETITIONS_KEY, repetitions)

        startActivity(intent)
    }
}



