package com.example.findhim.game

import android.os.Bundle

import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.findhim.R
import com.example.findhim.databinding.GameLayoutBinding
import com.example.findhim.fragments.game.GameFragment
import com.example.findhim.fragments.game.StatsFragment
import com.example.findhim.persistency.GameApplication
import com.example.findhim.persistency.GameViewModel
import com.example.findhim.persistency.GameViewModelFactory


class GameActivity : AppCompatActivity(), GameFragment.GameFragmentListener {


    private val GameViewModel: GameViewModel by viewModels {
        GameViewModelFactory((application as GameApplication).repository)
    }

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
            clicks = savedInstanceState.getInt("attempts")
            numCols = savedInstanceState.getInt("cols")
            numRows = savedInstanceState.getInt("rows")
//            chronometer.base = savedInstanceState.getLong("chrono_time")
//            chronometer.start()
        }

        // Get the input values passed from the MainActivity
        val backgroundImageId = intent.getIntExtra(SELECTED_LEVEL_IMAGE_KEY, 0)


        message = intent.getStringExtra(MESSAGE_KEY)
        cellSize = intent.getIntExtra(CELL_SIZE, 100)
        onBack()

        createStatsFragment(clicks)
        createGameFragment(cellSize, backgroundImageId, message)


    }
    //EXTERNALITZAR TOT
    //Implementar un timeout de x minuts, si s arriba al timeout game over , i es guarda la partida com a perduda, podem mostrar pop up ...


    private fun createGameFragment(cellSize: Int, backgroundImageId: Int, nickname: String?) {

        val fragment = GameFragment.newInstance(cellSize, backgroundImageId, nickname)
        // replace the placeholder container with the new fragment and commit
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_game, fragment)
            .commit()

    }

    private fun createStatsFragment(attempts: Int) {
        val existsFragment = supportFragmentManager.findFragmentById(R.id.fragment_stats)

        //If it exists, do no recreate
        if (existsFragment == null) {
            val fragment = StatsFragment.newInstance(attempts)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_stats, fragment)
                .commit()
        }

    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
////        onGetTime()?.let { outState.putLong("chrono_time", it.base) }
//        outState.putInt("wally_pos", imageIndex)
//        outState.putInt("attempts", clicks)
//        outState.putInt("cols", numCols)
//        outState.putInt("rows", numRows)
//    }

    override fun onDestroy() {
//        chronometer.stop()
        super.onDestroy()
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
}