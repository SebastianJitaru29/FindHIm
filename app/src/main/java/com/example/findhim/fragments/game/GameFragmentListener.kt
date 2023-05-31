package com.example.findhim.fragments.game

import android.widget.Chronometer

interface GameFragmentListener {
    fun onStopTime()
    fun onStartTime()
    fun onGetTime(): Chronometer?
    fun onUserClick()
    fun getAttempts(): Int?
    fun maxTimeReached()
}