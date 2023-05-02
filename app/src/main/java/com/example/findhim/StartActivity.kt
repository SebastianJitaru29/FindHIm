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
    private lateinit var waldoGif: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_layout)

        waldoGif = findViewById(R.id.waldo_walking)
        Glide.with(this).load(R.drawable.walkingwaldo).into(waldoGif)

        PlayerName = findViewById(R.id.firstInputEditText)
        val startGameButton = findViewById<Button>(R.id.saveButton)
        startGameButton.setOnClickListener { startGame() }
    }

    private fun startGame() {
        val message = PlayerName.text.toString()

        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra(GameActivity.MESSAGE_KEY, message)
        startActivity(intent)
    }
}



