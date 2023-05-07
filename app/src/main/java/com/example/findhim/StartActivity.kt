package com.example.findhim

import com.example.findhim.Game.GameActivity

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import java.lang.NumberFormatException

class StartActivity : AppCompatActivity() {
    private lateinit var PlayerName: EditText
    private lateinit var cellSize: EditText
    private lateinit var waldoGif: ImageView
    private var selectedLevelImage = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_layout)
        var toast: Toast? = null

        if (savedInstanceState != null) {
            selectedLevelImage = savedInstanceState.getInt("level")
        }

        val musicButton = findViewById<ImageView>(R.id.music)
        musicButton.setOnClickListener {
            if (MusicPlayer.isPlaying()) {
                musicButton.setBackgroundResource(R.drawable.audio_off)
                MusicPlayer.stop()
                toast?.cancel()
                toast = Toast.makeText(this, "Music Stopped", Toast.LENGTH_SHORT)
                toast?.show()
            } else {
                musicButton.setBackgroundResource(R.drawable.audio_on)
                MusicPlayer.start(this, R.raw.background_song)
                toast?.cancel()
                toast = Toast.makeText(this, "Music On", Toast.LENGTH_SHORT)
                toast?.show()
            }
        }
        waldoGif = findViewById(R.id.waldo_walking)
        Glide.with(this).load(R.drawable.walkingwaldo).into(waldoGif)
        PlayerName = findViewById(R.id.firstInputEditText)
        cellSize = findViewById(R.id.cellSizeInput)

        val startGameButton = findViewById<Button>(R.id.saveButton)
        val level1Btn = findViewById<Button>(R.id.level1)
        val level2Btn = findViewById<Button>(R.id.level2)
        val level3Btn = findViewById<Button>(R.id.level3)
        val level4Btn = findViewById<Button>(R.id.level4)
        // Set OnClickListener for level buttons

        if (selectedLevelImage != -1) {
            when (selectedLevelImage) {
                R.drawable.map1 -> setButtonBackground(level1Btn)
                R.drawable.map2 -> setButtonBackground(level2Btn)
                R.drawable.map3 -> setButtonBackground(level3Btn)
                R.drawable.map4 -> setButtonBackground(level4Btn)
            }
        }

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
        val cellSize = cellSize.text.toString()
        val cellInt: Int

        try {
            cellInt = when (cellSize.toInt()) {
                in (55..200) -> cellSize.toInt()
                else -> {
                    Toast.makeText(this, applicationContext.getText(R.string.error_waldo_size), Toast.LENGTH_SHORT).show()
                    return
                }
            }
        } catch (e: NumberFormatException){
            Toast.makeText(this, applicationContext.getText(R.string.error_waldo_size), Toast.LENGTH_SHORT).show()
            return
        }

        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra(GameActivity.MESSAGE_KEY, message)
        intent.putExtra(GameActivity.SELECTED_LEVEL_IMAGE_KEY, selectedLevelImage)
        intent.putExtra(GameActivity.CELL_SIZE, cellInt)
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("level", selectedLevelImage)
    }
}
