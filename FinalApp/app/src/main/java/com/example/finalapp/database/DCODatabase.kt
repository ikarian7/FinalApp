package com.example.finalapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.finalapp.model.DCOCharacter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [DCOCharacter::class], version = 2, exportSchema = false)
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
                                        database.dcoCharacterDAO().insertCharacter(DCOCharacter(1,  true, "Iris", "The circle", "Brawler", "is lief"))
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