package com.example.myapplication.ui.fragment.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.entitiy.CartDataEntity
import com.example.myapplication.data.soure.local.RoomDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(var room: RoomDao) : ViewModel() {
    private lateinit var cartList: LiveData<List<CartDataEntity>>


    fun getData(): LiveData<List<CartDataEntity>> {
        cartList = room.loadDataInCart()
        return cartList
    }


}