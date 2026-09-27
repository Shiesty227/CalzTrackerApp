package com.example.calztrackerapp.data.model.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WeightDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeight(weightEntry: WeightEntry)

    @Query("SELECT * FROM weight_table ORDER BY date ASC")
    fun getAllWeights(): LiveData<List<WeightEntry>>

    @Delete
    suspend fun deleteWeight(weightEntry: WeightEntry)
    @Query("DELETE FROM weight_table")
    suspend fun deleteAllWeights()

}
