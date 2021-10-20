package com.groupg.foodrecipe.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_data_table")
data class Food(

    @PrimaryKey(autoGenerate = true)
    var foodId : Long = 0L,

    @ColumnInfo(name = "food_name")
    var name : String = "",

    @ColumnInfo(name = "food_info")
    var info : String = "",

    @ColumnInfo(name = "food_recipe")
    var recipe : String = "",

    @ColumnInfo(name = "food_image_path")
    var imagePath: String = "",

    @ColumnInfo(name = "food_isFavourite")
    var favourite : Boolean = false,
)
