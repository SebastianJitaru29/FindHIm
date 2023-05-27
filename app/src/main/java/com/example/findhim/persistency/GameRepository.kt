package com.example.findhim.persistency

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class GameRepository(private val gameDao: GameDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allGames: Flow<List<Game>> = gameDao.getContactOrderedByPlayerName()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(game: Game) {
        gameDao.saveGame(game)
    }
    suspend fun delete(){
        gameDao.deleteAllGames()
    }
}
