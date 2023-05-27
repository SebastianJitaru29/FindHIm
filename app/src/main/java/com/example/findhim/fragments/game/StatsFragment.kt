package com.example.findhim.fragments.game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.example.findhim.R
import com.example.findhim.databinding.FragmentStatsBinding
import com.example.findhim.game.GameActivity


class StatsFragment : Fragment() {
    private lateinit var binding: FragmentStatsBinding
    private lateinit var gameActivity: GameActivity

    private var attempts: Int = 0
    private lateinit var chronometer: Chronometer
    private lateinit var attemptsTextView: TextView
    private var chronometerTime: Long = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.let {
            attempts = it.getInt(ARG_ATTEMPTS, 0)
        }
        chronometer = binding.chronometerr
        attemptsTextView = binding.attempts


        if (savedInstanceState != null) {
            attempts = savedInstanceState.getInt("attempts")
            chronometer.base = savedInstanceState.getLong("Time")
            chronometer.start()
        }
        attemptsTextView.text = attempts.toString()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("attempts", attempts)
        outState.putLong("Time", chronometer.base)
    }


    fun startTime() {
//        if (chronometerTime != 0L) {
//            chronometer.base = chronometerTime
//        }
        chronometer.start()
        Log.e("time", "Started")
    }

    fun stopTime() {
        chronometer.stop()
        saveTime()
    }

    fun getTime(): Chronometer {
        return chronometer
    }

    fun getAttempts(): Int {
        return attempts
    }

    fun incrementClick() {
        attempts++
        attemptsTextView.text = attempts.toString()
    }

    private fun saveTime() {
        chronometerTime = chronometer.base
    }


    companion object {
        //TODO set to strings.xml
        private const val ARG_ATTEMPTS = "arg_ATTEMPTS"

        fun newInstance(attempts: Int): StatsFragment {
            val fragment = StatsFragment()
            val args = Bundle()
            args.putInt(ARG_ATTEMPTS, attempts)
            fragment.arguments = args
            return fragment
        }
    }

}