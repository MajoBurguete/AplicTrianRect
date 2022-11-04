package com.example.aplictrianrect.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.aplictrianrect.models.Historial


@Database(
    entities = [Historial::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(AppConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun historialDao(): HistorialDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            if (INSTANCE == null) {
                synchronized(this) {
                    // Pass the database to the INSTANCE
                    INSTANCE = buildDatabase(context)
                }
            }
            // Return database.
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "aplictrian_database"
            )
                .allowMainThreadQueries()
                .build()
        }
    }
}