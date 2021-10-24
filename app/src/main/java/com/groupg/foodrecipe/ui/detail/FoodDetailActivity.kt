package com.groupg.foodrecipe.ui.detail

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.groupg.foodrecipe.MainActivity
import com.groupg.foodrecipe.R
import com.groupg.foodrecipe.adapter.FoodSliderAdapter
import com.groupg.foodrecipe.data.database.Food
import com.groupg.foodrecipe.data.database.FoodDatabase
import com.groupg.foodrecipe.data.model.SliderItem
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class FoodDetailActivity : AppCompatActivity() {

    private lateinit var adapter: FoodSliderAdapter

    private var food: Food? = null

    private val btnAddToCart by lazy { findViewById<Button>(R.id.addToCard) }
    private val ivAddToFavourites by lazy { findViewById<ImageView>(R.id.favourite) }

    private val sliderView by lazy { findViewById<SliderView>(R.id.foodImageSlider) }

    private val icBackToPreviousScreen by lazy { findViewById<ImageView>(R.id.backToPreviousScreenIcon) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        food = intent?.extras?.getParcelable<Food>("food")
        setContentView(R.layout.activity_detail)

        changeFavouriteComponentResource(food?.isFavourite)

        adapter = FoodSliderAdapter(this)
        setAdapterToFoodSliderView(adapter)
        initFoodSliderView()

        populateFoodSliderAdapter()
    }

    private fun changeFavouriteComponentResource(isFavourite: Boolean?) {
        if (isFavourite == true) {
            ivAddToFavourites.setImageResource(R.drawable.ic_favorite_filled)
            ivAddToFavourites.tag = R.drawable.ic_favorite_filled
        } else {
            ivAddToFavourites.setImageResource(R.drawable.ic_favorite_empty)
            ivAddToFavourites.tag = R.drawable.ic_favorite_empty
        }
    }

    private fun setAdapterToFoodSliderView(adapter: FoodSliderAdapter) {
        sliderView.setSliderAdapter(this.adapter)
    }

    private fun initFoodSliderView() {
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM) //set indicator animation by using SliderLayout.IndicatorAnimations.
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        sliderView.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
        sliderView.indicatorSelectedColor = Color.WHITE
        sliderView.indicatorUnselectedColor = Color.GRAY
        sliderView.scrollTimeInSec = 3
        sliderView.isAutoCycle = true
        sliderView.startAutoCycle()
    }

    private fun populateFoodSliderAdapter() {
        addNewItem(
            "Food Image1",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRzSTchONYhox1rTXj4n__tlcQwEOKSEPtUGA&usqp=CAU"
        )
        addNewItem(
            "Food Image2",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ30SJ8ifAO5F1bDdMjFz77-d13Lz5ZEFU3Tw&usqp=CAU"
        )
        addNewItem(
            "Food Image3",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR7Jv4kfuxOELeOWtynoHJgqpZ480QLRuuRaA&usqp=CAU"
        )
    }

    private fun addNewItem(description: String, imageUrl: String) {
        val sliderItem = SliderItem(description, imageUrl)
        adapter.addItem(sliderItem)
    }

    fun addToCartClicked(view: View) {
    }

    fun addFavourite(view: View) {
        if (ivAddToFavourites.tag as Int == R.drawable.ic_favorite_filled) {
            Log.e("filled", "filled")
        } else {
            Log.e("empty", "empty")
        }

        /*
        val foodDatabaseDao =  FoodDatabase.getInstance(this@FoodDetailActivity).foodDatabaseDao
        GlobalScope.launch {
            val food: Food = foodDatabaseDao.getFood(food!!.foodId)
            food.isFavourite = true
            foodDatabaseDao.updateFood(food)
        }
        */
    }

    fun backToPreviousPage(view: View) {
        startActivity(Intent(this@FoodDetailActivity, MainActivity::class.java))
    }
}