package com.example.finalapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "weaponItemTable", foreignKeys = [
    ForeignKey(
        entity = DCOCharacter::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("characterID"),
        onDelete = ForeignKey.CASCADE
    )
])
data class WeaponItem (
    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "toHit")
    var toHit: Int,

    @ColumnInfo(name = "characterID")
    var characterID: Long? = null,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)