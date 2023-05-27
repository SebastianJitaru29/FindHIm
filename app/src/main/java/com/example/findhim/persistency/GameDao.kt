package com.example.findhim.persistency

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Delete() //conflicts?
    suspend fun deleteGame(game:Game)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveGame(game:Game)

    @Query("SELECT * FROM game_table ORDER BY id ASC")
    fun getContactOrderedByPlayerName(): Flow<List<Game>>

    @Query("SELECT * FROM game_table ORDER BY game_time ASC")
    fun getContactOrderedByGameTime(): Flow<List<Game>>

    @Query("DELETE FROM game_table")
    suspend fun deleteAllGames()

}