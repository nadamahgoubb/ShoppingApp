package com.example.myapplication.ui.fragment.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.entitiy.CartDataEntity
import com.example.myapplication.data.local.RoomDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(var room: RoomDao) : ViewModel() {
    private lateinit var cartList: LiveData<List<CartDataEntity>>


    fun getData(): LiveData<List<CartDataEntity>> {
        cartList = room.loadDataInCart()
        return cartList
    }

    fun addToCart(cartItem: CartDataEntity) {
        GlobalScope.launch {
            room.insertInCart(cartItem)
        }
    }
    suspend fun removeFromCart(cartItem: CartDataEntity) {
        room.deleteFromCart(cartItem)
    }
    suspend fun removeAll() {
        room.deleteAllCart()
    }

}