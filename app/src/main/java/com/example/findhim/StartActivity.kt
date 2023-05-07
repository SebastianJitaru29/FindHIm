package com.example.findhim

import com.example.findhim.Game.GameActivity

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import java.lang.NumberFormatException

class StartActivity : AppCompatActivity() {
    private lateinit var playerName: EditText
    private lateinit var cellSize: EditText
    private lateinit var waldoGif: ImageView
    private var selectedLevelImage = -1
    private var lastPressedButtonId = 0

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
                toast = Toast.makeText(this, getString(R.string.music_off), Toast.LENGTH_SHORT)
                toast?.show()
            } else {
                musicButton.setBackgroundResource(R.drawable.audio_on)
                MusicPlayer.start(this, R.raw.background_song)
                toast?.cancel()
                toast = Toast.makeText(this, getString(R.string.music_on), Toast.LENGTH_SHORT)
                toast?.show()
            }
        }

        //Set gif
        waldoGif = findViewById(R.id.waldo_walking)
        Glide.with(this).load(R.drawable.walkingwaldo).into(waldoGif)

        playerName = findViewById(R.id.firstInputEditText)
        cellSize = findViewById(R.id.cellSizeInput)

        val startGameButton = findViewById<Button>(R.id.saveButton)
        startGameButton.setOnClickListener { startGame() }

        val levelButtons = listOf<Button>(
            findViewById(R.id.level1),
            findViewById(R.id.level2),
            findViewById(R.id.level3),
            findViewById(R.id.level4)
        )
        val levelImageIds = listOf(
            R.drawable.map1,
            R.drawable.map2,
            R.drawable.map3,
            R.drawable.map4
        )
        // Set OnClickListener for level buttons
        for ((index, button) in levelButtons.withIndex()) {

            button.setOnClickListener {
                selectedLevelImage = levelImageIds[index]
                setButtonBackground(button)
            }
        }
    }

    private fun startGame() {
        val message = playerName.text.toString()
        val cellSize = cellSize.text.toString()

        try {
            val cellInt = cellSize.toInt()
            if (cellInt !in (55..200)) {
                Toast.makeText(this, getString(R.string.error_waldo_size), Toast.LENGTH_SHORT)
                    .show()
                return
            }
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra(GameActivity.MESSAGE_KEY, message)
            intent.putExtra(GameActivity.SELECTED_LEVEL_IMAGE_KEY, selectedLevelImage)
            intent.putExtra(GameActivity.CELL_SIZE, cellInt)
            startActivity(intent)

        } catch (e: NumberFormatException) {
            Toast.makeText(this, getString(R.string.error_waldo_size), Toast.LENGTH_SHORT)
                .show()
            return
        }
    }

    private fun setButtonBackground(button: Button) {
        //If last pressed is same, do nothing
        if (lastPressedButtonId == button.id)
            return

        //else change colors of last pressed button
        if (lastPressedButtonId != 0) {
            val lastPressedButton = findViewById<Button>(lastPressedButtonId)
            lastPressedButton.setBackgroundResource(R.drawable.not_clicked)
            lastPressedButton.setTextColor(ContextCompat.getColor(this, R.color.light_red))
        }

        //Change currently pressed
        button.setBackgroundResource(R.drawable.when_clicked)
        button.setTextColor(ContextCompat.getColor(this, R.color.white))

        //save
        lastPressedButtonId = button.id
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("level", selectedLevelImage)
    }
}
