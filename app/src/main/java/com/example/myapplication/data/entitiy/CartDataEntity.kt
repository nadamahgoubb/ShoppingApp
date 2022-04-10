package com.example.myapplication.data.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CartTable")
data class CartDataEntity( @PrimaryKey(autoGenerate = true)
                           val id: Int,
                           val description: String?,
                           val discount: Int?,
                           val image: String?,
                           val images: List<String>?,
                           val in_cart: Boolean?,
                           val in_favorites: Boolean?,
                           val name: String?,
                           val old_price: Float?,
                           val price: Float?,
                           var quantity: Int? = 0) {

}