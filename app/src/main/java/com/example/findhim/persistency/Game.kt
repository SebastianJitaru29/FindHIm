package com.example.findhim.persistency

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_table")
data class Game(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "nickname") val nickname: String?,
    @ColumnInfo(name = "clicks") val clicks: String?,
    @ColumnInfo(name = "game_time") val gameTime: String?,
//    @ColumnInfo(name = "game_date") val date: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
//        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(nickname)
        parcel.writeString(clicks)
        parcel.writeString(gameTime)
//        parcel.writeString(date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Game> {
        override fun createFromParcel(parcel: Parcel): Game {
            return Game(parcel)
        }

        override fun newArray(size: Int): Array<Game?> {
            return arrayOfNulls(size)
        }
    }
}
