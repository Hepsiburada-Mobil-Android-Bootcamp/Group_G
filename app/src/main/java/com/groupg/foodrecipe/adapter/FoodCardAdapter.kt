package com.groupg.foodrecipe.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.groupg.foodrecipe.databinding.FoodCardItemBinding

class FoodCardAdapter(var dataSet:String):RecyclerView.Adapter<FoodCardAdapter.CardViewHolder>()
{

    inner class CardViewHolder(binding:FoodCardItemBinding):RecyclerView.ViewHolder(binding.root)
    {

    val binding:FoodCardItemBinding=binding


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val binding:FoodCardItemBinding= FoodCardItemBinding.inflate(inflater,parent,false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
       val data=dataSet

        Glide.with(holder.binding.root)
            .load("https://i.imgur.com/tGbaZCY.jpg")
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .transform(CircleCrop())
            .into(holder.binding.imageViewFoodImage)

        holder.binding.foodName="Yemek Adı"


    }

    override fun getItemCount(): Int =4


}