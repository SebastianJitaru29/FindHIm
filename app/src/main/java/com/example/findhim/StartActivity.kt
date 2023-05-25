package com.example.findhim

import com.example.findhim.game.GameActivity

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.example.findhim.databinding.StartLayoutBinding
import com.example.findhim.fragments.StartActivity.DifficultySelector
import com.example.findhim.utils.MusicPlayer
import java.lang.NumberFormatException

class StartActivity : AppCompatActivity() {
    private lateinit var playerName: EditText
    private lateinit var cellSize: EditText
    private lateinit var waldoGif: ImageView
    private var selectedLevelImage = -1
    private var lastPressedButtonId = 0

    private lateinit var binding: StartLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = StartLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            selectedLevelImage = savedInstanceState.getInt(getString(R.string.LEVEL))
            lastPressedButtonId = savedInstanceState.getInt(getString(R.string.BUTTON_SELECTED))

            if (lastPressedButtonId != 0) {
                val lastPressedButton = findViewById<Button>(lastPressedButtonId)
                lastPressedButton.setBackgroundResource(R.drawable.when_clicked)
                lastPressedButton.setTextColor(ContextCompat.getColor(this, R.color.white))
            }
        }
        MusicPlayer.setupMusicButton(this)

        binding.lev?.setOnClickListener { showDifficultySelector() }

        //Set gif
        waldoGif = binding.waldoWalking
        Glide.with(this).load(R.drawable.walkingwaldo).into(waldoGif)

        playerName = binding.firstInputEditText
        cellSize = binding.cellSizeInput

        val startGameButton = binding.saveButton
        startGameButton.setOnClickListener { startGame() }

        val levelButtons = listOf(
            binding.level1,
            binding.level2,
            binding.level3,
            binding.level4
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

    fun setButtonBackground(button: Button) {
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

    private fun showDifficultySelector() {
        val fragmentManager = supportFragmentManager
        val sizeFragment = fragmentManager.findFragmentById(R.id.fragment) as? DifficultySelector

        if (sizeFragment == null) {
            val fragment = DifficultySelector()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, fragment)
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("level", selectedLevelImage)
        outState.putInt("button", lastPressedButtonId)
    }

    override fun onPause() {
        super.onPause()
        MusicPlayer.updateMusicButton(this)
    }

    override fun onResume() {
        super.onResume()
        MusicPlayer.updateMusicButton(this)
    }
}
