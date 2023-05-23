package com.example.findhim.persistency

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Delete() //conflicts?
    suspend fun deleteGame(game:Game)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveGame(game:Game)

    @Query("SELECT * FROM game ORDER BY playerName ASC")
    fun getContactOrderedByPlayerName(): Flow<List<Game>>

    @Query("SELECT * FROM game ORDER BY gameTime ASC")
    fun getContactOrderedByGameTime(): Flow<List<Game>>

}