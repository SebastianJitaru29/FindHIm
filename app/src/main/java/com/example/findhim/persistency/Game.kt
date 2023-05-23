package com.example.findhim.persistency

import androidx.room.Entity
import androidx.room.PrimaryKey
//game class, table in the database
//order by time, by id i delete
@Entity
data class Game(
    val playerName:String,
    val levelPlayed:String,
    val gameTime:String,
    val clicks:String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)
