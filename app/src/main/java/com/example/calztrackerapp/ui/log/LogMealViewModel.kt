package com.example.calztrackerapp.ui.log

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.calztrackerapp.data.model.db.AppDatabase
import com.example.calztrackerapp.data.model.db.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LogMealViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)
    private val mealDao = db.mealDao()

    // 🔢 LiveData totals for macros
    val totalCalories: LiveData<Int?> = mealDao.getTotalCalories()
    val totalProtein: LiveData<Int?> = mealDao.getTotalProtein()
    val totalCarbs: LiveData<Int?> = mealDao.getTotalCarbs()
    val totalFats: LiveData<Int?> = mealDao.getTotalFats()

    // Meal insertion function
    fun insertMeal(meal: Meal) {
        viewModelScope.launch(Dispatchers.IO) {
            mealDao.insertMeal(meal)
        }
    }
}
