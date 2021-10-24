package com.groupg.foodrecipe.data.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FoodDatabaseDao {

    // Insert new food to the database
    @Insert
    fun insertFood(food: Food )

    // Update food in the database
    @Update
    fun updateFood(food: Food )

    // Get food from the database
    @Query("SELECT * FROM food_data_table WHERE foodId = :key")
    fun getFood(key: Long): Food

    // Delete a food from the database
    @Delete
    fun deleteFood(food: Food)

    // Delete all food from the database
    @Query("DELETE FROM food_data_table")
    fun clear()

    // Get all food from the database
    @Query("SELECT * FROM food_data_table ORDER BY foodId ASC")
    fun getAllFood(): LiveData<List<Food>>

    // Get favourite food from the database
    @Query("SELECT * FROM food_data_table WHERE food_isFavourite ORDER BY foodId DESC")
    fun getFavouriteFood(): LiveData<List<Food>>
}