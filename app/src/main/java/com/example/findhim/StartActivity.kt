package com.example.findhim

import com.example.findhim.GameActivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class StartActivity : AppCompatActivity() {
    private lateinit var PlayerName: EditText
    private lateinit var NumberOfTrys: EditText
    private lateinit var Rows: EditText
    private lateinit var Columns: EditText
    private lateinit var waldoGif: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_layout)

        waldoGif = findViewById(R.id.waldo_walking)
        Glide.with(this).load(R.drawable.walkingwaldo).into(waldoGif)

        PlayerName = findViewById(R.id.firstInputEditText)
        NumberOfTrys = findViewById(R.id.secondInputEditText)
        Rows = findViewById(R.id.thirdInputEditText)
        Columns = findViewById(R.id.fourthInputEditText)
        val startGameButton = findViewById<Button>(R.id.saveButton)
        startGameButton.setOnClickListener { startGame() }
    }

    private fun startGame() {
        val message = PlayerName.text.toString()
        val repetitions = NumberOfTrys.text.toString().toIntOrNull() ?: 0
        val row = Rows.text.toString().toIntOrNull() ?: 0
        val column = Columns.text.toString().toIntOrNull() ?: 0

        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra(GameActivity.MESSAGE_KEY, message)
        intent.putExtra(GameActivity.REPETITIONS_KEY, repetitions)
        intent.putExtra(GameActivity.ROWS_KEY, row)
        intent.putExtra(GameActivity.COLUMNS_KEY, column)
        startActivity(intent)
    }
}



