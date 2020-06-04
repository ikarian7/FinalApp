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

@Database(entities = [DCOCharacter::class], version = 1, exportSchema = false)
abstract class DnDDatabase : RoomDatabase() {
    abstract fun dcoCharacterDAO(): DCOCharacterDAO

    companion object {
        private const val DATABASE_NAME = "CHARACTERS_DATABASE"

        @Volatile
        private var INSTANCE: DnDDatabase? = null

        fun getDatabase(context: Context): DnDDatabase? {
            if (INSTANCE == null) {
                synchronized(DnDDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = databaseBuilder(context.applicationContext, DnDDatabase::class.java, DATABASE_NAME).fallbackToDestructiveMigration().addCallback(object : RoomDatabase.Callback() {
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