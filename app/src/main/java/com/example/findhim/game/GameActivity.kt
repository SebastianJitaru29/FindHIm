
package com.example.findhim.game
import android.os.Bundle

import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.findhim.R
import com.example.findhim.databinding.GameLayoutBinding
import com.example.findhim.fragments.game.GameFragment
import com.example.findhim.fragments.game.GameFragmentListener
import com.example.findhim.fragments.game.StatsFragment


class GameActivity : AppCompatActivity(), GameFragmentListener {


    lateinit var binding: GameLayoutBinding

    private var cellSize: Int = 0
    var clicks: Int = 0
    private var imageIndex: Int = -1
    private var numCols: Int = 0
    private var numRows: Int = 0
    private var message: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = GameLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (savedInstanceState != null) {
            imageIndex = savedInstanceState.getInt("wally_pos")
            numCols = savedInstanceState.getInt("cols")
            numRows = savedInstanceState.getInt("rows")
        }

        // Get the input values passed from the MainActivity
        val backgroundImageId = intent.getIntExtra(SELECTED_LEVEL_IMAGE_KEY, 0)
        message = intent.getStringExtra(MESSAGE_KEY)
        cellSize = intent.getIntExtra(CELL_SIZE, 100)

        onBack()

        createStatsFragment()
        createGameFragment(cellSize, backgroundImageId, message)
    }
    //EXTERNALITZAR TOT
    //Implementar un timeout de x minuts, si s arriba al timeout game over , i es guarda la partida com a perduda, podem mostrar pop up ...


    private fun createGameFragment(cellSize: Int, backgroundImageId: Int, nickname: String?) {
        val existsFragment = supportFragmentManager.findFragmentById(R.id.fragment_game)

        //If does not exists, create a new one, if not update values
        if (existsFragment == null) {
            val fragment = GameFragment.newInstance(cellSize, backgroundImageId, nickname)
            // replace the placeholder container with the new fragment and commit
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_game, fragment)
                .commit()
        } else {
            val gameFragment = existsFragment as GameFragment
            gameFragment.update(cellSize, backgroundImageId, nickname)
        }

    }

    private fun createStatsFragment() {
        val existsFragment = supportFragmentManager.findFragmentById(R.id.fragment_stats)

        //If it exists, do not recreate
        if (existsFragment == null) {
            val fragment = StatsFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_stats, fragment)
                .commit()
        }

    }

    private fun onBack() {
        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    // If back is pressed finish the activity
                    finish()
                }
            })
    }

    companion object {
        const val MESSAGE_KEY = "message"
        const val SELECTED_LEVEL_IMAGE_KEY = "selectedLevelImage"
        const val CELL_SIZE = "cellsize"
    }

    override fun onStopTime() {
        val statsFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_stats) as? StatsFragment
        statsFragment?.stopTime()
    }

    override fun onStartTime() {
        val statsFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_stats) as? StatsFragment
        statsFragment?.startTime()
    }

    override fun onGetTime(): Chronometer? {
        val statsFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_stats) as? StatsFragment
        return statsFragment?.getTime()
    }

    override fun onUserClick() {
        val statsFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_stats) as? StatsFragment
        statsFragment?.incrementClick()
    }

    override fun getAttempts(): Int? {
        val statsFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_stats) as? StatsFragment
        return statsFragment?.getAttempts()
    }


    override fun maxTimeReached() {
        val gameFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_game) as? GameFragment
        gameFragment?.maxTime()
    }


}