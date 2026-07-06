package com.example.triza_dream.catatan

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BansosEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bansosDao(): BansosDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "triza_bansos_database"
                )
                .fallbackToDestructiveMigration() // PENGAMAN: Mencegah crash jika struktur tabel berubah
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}