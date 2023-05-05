package com.example.findhim

import com.example.findhim.GameActivity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class StartActivity : AppCompatActivity() {
    private lateinit var PlayerName: EditText
    private lateinit var waldoGif: ImageView
    private var selectedLevelImage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_layout)
        val stopButton = findViewById<ImageView>(R.id.audiooff)
        stopButton.setOnClickListener {
            MusicPlayer.stop()
            Toast.makeText(this, "Music Stopped", Toast.LENGTH_SHORT).show()

        }
        val onButton = findViewById<ImageView>(R.id.audioon)
        onButton.setOnClickListener{
            MusicPlayer.start(this,R.raw.background_song)
            Toast.makeText(this, "Music On", Toast.LENGTH_SHORT).show()

        }
        waldoGif = findViewById(R.id.waldo_walking)
        Glide.with(this).load(R.drawable.walkingwaldo).into(waldoGif)
        PlayerName = findViewById(R.id.firstInputEditText)

        val startGameButton = findViewById<Button>(R.id.saveButton)
        val level1Btn = findViewById<Button>(R.id.level1)
        val level2Btn = findViewById<Button>(R.id.level2)
        val level3Btn = findViewById<Button>(R.id.level3)
        val level4Btn = findViewById<Button>(R.id.level4)
       // Set OnClickListener for level buttons
        level1Btn.setOnClickListener {
            selectedLevelImage = R.drawable.map1
            setButtonBackground(level1Btn)
        }
        level2Btn.setOnClickListener {
            selectedLevelImage = R.drawable.map2
            setButtonBackground(level2Btn)
        }
        level3Btn.setOnClickListener {
            selectedLevelImage = R.drawable.map3
            setButtonBackground(level3Btn)
        }
        level4Btn.setOnClickListener {
            selectedLevelImage = R.drawable.map4
            setButtonBackground(level4Btn)
        }
        startGameButton.setOnClickListener { startGame() }
    }

    private fun startGame() {
        val message = PlayerName.text.toString()

        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra(GameActivity.MESSAGE_KEY, message)
        intent.putExtra(GameActivity.SELECTED_LEVEL_IMAGE_KEY, selectedLevelImage)
        startActivity(intent)
    }

    private fun setButtonBackground(button: Button) {
        // Set clicked button's background to "clicked_btn" drawable
        button.setBackgroundResource(R.drawable.when_clicked)

        // Reset the other buttons' backgrounds to the original drawable
        when (button.id) {
            R.id.level1 -> {
                findViewById<Button>(R.id.level2).setBackgroundResource(R.drawable.grey_rectangle)
                findViewById<Button>(R.id.level3).setBackgroundResource(R.drawable.grey_rectangle)
                findViewById<Button>(R.id.level4).setBackgroundResource(R.drawable.grey_rectangle)
            }
            R.id.level2 -> {
                findViewById<Button>(R.id.level1).setBackgroundResource(R.drawable.grey_rectangle)
                findViewById<Button>(R.id.level3).setBackgroundResource(R.drawable.grey_rectangle)
                findViewById<Button>(R.id.level4).setBackgroundResource(R.drawable.grey_rectangle)
            }
            R.id.level3 -> {
                findViewById<Button>(R.id.level1).setBackgroundResource(R.drawable.grey_rectangle)
                findViewById<Button>(R.id.level2).setBackgroundResource(R.drawable.grey_rectangle)
                findViewById<Button>(R.id.level4).setBackgroundResource(R.drawable.grey_rectangle)
            }
            R.id.level4 -> {
                findViewById<Button>(R.id.level1).setBackgroundResource(R.drawable.grey_rectangle)
                findViewById<Button>(R.id.level2).setBackgroundResource(R.drawable.grey_rectangle)
                findViewById<Button>(R.id.level3).setBackgroundResource(R.drawable.grey_rectangle)
            }
        }
    }
}
