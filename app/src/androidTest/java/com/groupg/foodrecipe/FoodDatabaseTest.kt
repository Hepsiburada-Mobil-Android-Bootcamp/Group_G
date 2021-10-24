package com.groupg.foodrecipe

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.groupg.foodrecipe.data.database.FoodDatabase
import com.groupg.foodrecipe.data.database.FoodDatabaseDao
import com.groupg.foodrecipe.data.database.Food
import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class FoodDatabaseTest {

    private lateinit var foodDao: FoodDatabaseDao
    private lateinit var db: FoodDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, FoodDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        foodDao = db.foodDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetFavourite() {
        val food = Food()
        foodDao.insertFood(food)
        val testFood = foodDao.getFood(food.foodId)
        assertEquals(testFood.isFavourite, false)
    }
}