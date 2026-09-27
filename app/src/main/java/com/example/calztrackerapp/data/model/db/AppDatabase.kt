package com.example.calztrackerapp.data.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Meal::class, WeightEntry::class], // ⬅️ Added WeightEntry
    version = 3, // ⬆️ Bump version to avoid crash
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun mealDao(): MealDao
    abstract fun weightDao(): WeightDao // ⬅️ Add DAO

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "meal_db"
                )
                    .fallbackToDestructiveMigration() // 🔄 Resets db on schema change
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
