package com.example.calztrackerapp.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.calztrackerapp.data.model.db.AppDatabase
import com.example.calztrackerapp.data.model.db.Meal

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val mealDao = AppDatabase.getDatabase(application).mealDao()
    val recentMeals: LiveData<List<Meal>> = mealDao.getRecentMeals()

    val totalCalories: LiveData<Int?> = mealDao.getTotalCalories()
    val totalProtein: LiveData<Int?> = mealDao.getTotalProtein()
    val totalCarbs: LiveData<Int?> = mealDao.getTotalCarbs()
    val totalFats: LiveData<Int?> = mealDao.getTotalFats()
}
