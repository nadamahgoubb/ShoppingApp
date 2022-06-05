package com.example.myapplication.ui.fragment.wishList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.entitiy.CartDataEntity
import com.example.myapplication.data.entitiy.DataX
import com.example.myapplication.data.soure.local.RoomDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishListViewModel @Inject constructor(var room: RoomDao) : ViewModel() {
    private lateinit var wishList: LiveData<List<DataX>>


    fun getData(): LiveData<List<DataX>> {
        wishList = room.loadProducts()
        return wishList
    }

    fun addToCart(cartItem: CartDataEntity) {
        GlobalScope.launch {
            room.insertInCart(cartItem)
        }
    }


}