package com.example.finalapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.finalapp.model.DCOCharacter
import com.example.finalapp.model.WeaponItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [DCOCharacter::class, WeaponItem::class], version = 6, exportSchema = false)
abstract class DCODatabase : RoomDatabase() {
    abstract fun dcoCharacterDAO(): DCOCharacterDAO

    companion object {
        private const val DATABASE_NAME = "CHARACTERS_DATABASE"

        @Volatile
        private var INSTANCE: DCODatabase? = null

        fun getDatabase(context: Context): DCODatabase? {
            if (INSTANCE == null) {
                synchronized(DCODatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = databaseBuilder(context.applicationContext, DCODatabase::class.java, DATABASE_NAME).fallbackToDestructiveMigration().addCallback(object : RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                INSTANCE?.let { database ->
                                    CoroutineScope(Dispatchers.IO).launch {
                                        database.dcoCharacterDAO().insertCharacter(DCOCharacter(true, "Iris", "The Circle", "Assassin", "is lief", "blabla", 0, "bla", "bla", "bla", 100, 3, 1))
                                        database.dcoCharacterDAO().insertWeapon(WeaponItem("Saskia",30, 1))
                                    }
                                }
                            }
                        }).build()
                    }
                }
            }
            return INSTANCE
        }
    }
}