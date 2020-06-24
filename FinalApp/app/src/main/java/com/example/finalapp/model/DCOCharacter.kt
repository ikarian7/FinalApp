package com.example.finalapp.model

import android.text.Editable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dcoCharacterTable")
data class DCOCharacter (
    @ColumnInfo(name = "active")
    var active: Boolean,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "cartel")
    var cartel: String,

    @ColumnInfo(name = "profession")
    var profession: String,

    @ColumnInfo(name = "quirk")
    var quirk: String,

    @ColumnInfo(name = "inconplaces")
    var inconPlaces: String,

    @ColumnInfo(name = "conflictBar")
    var conflictbar: Int,

    @ColumnInfo(name = "story")
    var story: String,

    @ColumnInfo(name = "rewards")
    var rewards: String,

    @ColumnInfo(name = "background")
    var background: String,

    @ColumnInfo(name = "luck")
    var luck: Int,

    @ColumnInfo(name = "wounds")
    var wounds: Int,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)