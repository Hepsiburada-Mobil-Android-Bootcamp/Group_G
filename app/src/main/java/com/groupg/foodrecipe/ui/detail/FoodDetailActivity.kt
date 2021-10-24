package com.groupg.foodrecipe.ui.detail

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.groupg.foodrecipe.R
import com.groupg.foodrecipe.adapter.FoodSliderAdapter
import com.groupg.foodrecipe.data.model.SliderItem
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController.ClickListener
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView


class FoodDetailActivity : AppCompatActivity() {

    private lateinit var sliderView: SliderView
    private lateinit var adapter: FoodSliderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        sliderView = findViewById(R.id.foodImageSlider)
        //sliderView = findViewById<View>(R.id.foodImageSlider)

        adapter = FoodSliderAdapter(this)
        sliderView.setSliderAdapter(adapter)
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM) //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!

        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        sliderView.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
        sliderView.indicatorSelectedColor = Color.WHITE
        sliderView.indicatorUnselectedColor = Color.GRAY
        sliderView.scrollTimeInSec = 3
        sliderView.isAutoCycle = true
        sliderView.startAutoCycle()


        sliderView.setOnIndicatorClickListener(ClickListener {
            Log.i(
                "GGG",
                "onIndicatorClicked: " + sliderView.currentPagePosition
            )
        })

        addNewItem("Food Image1", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRzSTchONYhox1rTXj4n__tlcQwEOKSEPtUGA&usqp=CAU")
        addNewItem("Food Image2", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ30SJ8ifAO5F1bDdMjFz77-d13Lz5ZEFU3Tw&usqp=CAU")
        addNewItem("Food Image3", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR7Jv4kfuxOELeOWtynoHJgqpZ480QLRuuRaA&usqp=CAU")
    }

    private fun addNewItem(description:String, imageUrl:String) {
        val sliderItem = SliderItem(description, imageUrl)
        adapter.addItem(sliderItem)
    }
}