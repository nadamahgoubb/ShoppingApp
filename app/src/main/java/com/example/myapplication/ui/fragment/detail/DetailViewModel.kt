package com.example.myapplication.ui.fragment.detail

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
class DetailViewModel @Inject constructor(var room: RoomDao) : ViewModel() {
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

    fun insertFav(productItem: DataX) {
        GlobalScope.launch {
            room.insertProductFav(productItem)
        }
    }

    fun deletFav(productItem: DataX) {
        GlobalScope.launch {
            room.deleteProductFav(productItem)

        }
    }

    fun fetchProductById(name: String?): DataX {
        var product :DataX
            product = room.fetchById(name)!!
        return product

    }

}