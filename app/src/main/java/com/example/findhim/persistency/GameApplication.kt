package com.example.findhim.persistency

import android.app.Application

class GameApplication : Application(){
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { GameRoomDatabase.getDatabase(this) }
    val repository by lazy { GameRepository(database.gameDao()) }
}