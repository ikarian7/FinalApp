package com.example.finalapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dcoCharacterTable")
data class DCOCharacter (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null,

    @ColumnInfo(name = "active")
    var active: Boolean,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "cartel")
    var cartel: String,

    @ColumnInfo(name = "profession")
    var profession: String,

    @ColumnInfo(name = "quirk")
    var quirk: String
)