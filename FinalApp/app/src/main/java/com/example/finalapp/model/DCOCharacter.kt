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

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)