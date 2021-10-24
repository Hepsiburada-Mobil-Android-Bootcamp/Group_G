package com.groupg.foodrecipe.data.database

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_data_table")
data class Food(

    @PrimaryKey(autoGenerate = true)
    var foodId: Long = 0L,

    @ColumnInfo(name = "food_name")
    var name: String? = "",

    @ColumnInfo(name = "food_info")
    var info: String? = "",

    @ColumnInfo(name = "food_recipe")
    var recipe: String? = "",

    @ColumnInfo(name = "food_image_path")
    var imagePath: String? = "",

    @ColumnInfo(name = "food_isFavourite")
    var isFavourite: Boolean = false,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(foodId)
        parcel.writeString(name)
        parcel.writeString(info)
        parcel.writeString(recipe)
        parcel.writeString(imagePath)
        parcel.writeByte(if (isFavourite) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Food> {
        override fun createFromParcel(parcel: Parcel): Food {
            return Food(parcel)
        }

        override fun newArray(size: Int): Array<Food?> {
            return arrayOfNulls(size)
        }
    }
}
