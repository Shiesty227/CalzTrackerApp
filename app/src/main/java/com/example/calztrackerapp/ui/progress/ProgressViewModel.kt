package com.example.calztrackerapp.ui.progress

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.calztrackerapp.data.model.db.AppDatabase
import com.example.calztrackerapp.data.model.db.WeightEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope


class ProgressViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)

    // Observes the full weight history from the DB
    val weightHistory = db.weightDao().getAllWeights()

    fun saveWeight(weight: Float, date: String) {
        viewModelScope.launch(Dispatchers.IO) {
            db.weightDao().insertWeight(
                WeightEntry(
                    weight = weight,
                    date = date
                )
            )
        }
    }
}
