package com.example.calztrackerapp.data.model.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: Meal)

    @Query("SELECT * FROM meal_table")
    suspend fun getAllMeals(): List<Meal>

    @Delete
    suspend fun deleteMeal(meal: Meal)

    // 🔢 Macro Totals
    @Query("SELECT SUM(calories) FROM meal_table")
    fun getTotalCalories(): LiveData<Int?>

    @Query("SELECT SUM(protein) FROM meal_table")
    fun getTotalProtein(): LiveData<Int?>

    @Query("SELECT SUM(carbs) FROM meal_table")
    fun getTotalCarbs(): LiveData<Int?>

    @Query("SELECT SUM(fats) FROM meal_table")
    fun getTotalFats(): LiveData<Int?>
    @Query("DELETE FROM meal_table")
    suspend fun deleteAllMeals()
    @Query("SELECT * FROM meal_table ORDER BY timestamp DESC LIMIT 8")
    fun getRecentMeals(): LiveData<List<Meal>>


}
