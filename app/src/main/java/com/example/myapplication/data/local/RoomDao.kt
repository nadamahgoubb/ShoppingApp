package com.example.myapplication.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.data.entitiy.CartDataEntity
import com.example.myapplication.data.entitiy.DataX

@Dao
interface RoomDao {
    //WishListDao
    @Query("SELECT * FROM ProductDetails")
    fun loadProducts(): LiveData<List<DataX>>

    @Query("SELECT * FROM ProductDetails")
    fun loadProductss(): List<DataX>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProductFav(product: DataX): Long

    @Delete
    suspend fun deleteProductFav(product: DataX?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateProduct(product: DataX?)

    @Query("SELECT * FROM ProductDetails WHERE name = :name")
    fun fetchById(name: String?): DataX?

    @Query("DELETE FROM ProductDetails")
    suspend fun deleteAll()

    //cart
    @Query("SELECT * FROM CartTable")
    fun loadDataInCart(): LiveData<List<CartDataEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
      fun insertInCart(product: CartDataEntity): Long

    @Delete
    suspend fun deleteFromCart(product: CartDataEntity?)


    @Query("SELECT * FROM CartTable WHERE name = :name")
    fun fetchCartItemById(name: String?): CartDataEntity?

    @Query("DELETE FROM CartTable")
    suspend fun deleteAllCart()


    /* @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(product: DataX): Long

     @Query("SELECT * FROM ProductDetails")
     fun loadAll(): MutableList<DataX>

     @Delete
     fun delete(product: DataX)

     @Query("DELETE FROM ProductDetails")
     fun deleteAll()

     @Query("SELECT * FROM ProductDetails where id = :productId")
     fun loadOneByProductId(productId: Int): DataX?

     @Query("SELECT * FROM ProductDetails where name = :name")
     fun loadOneByProductName(name: String): DataX?

     @Update
     fun update(ProductDetails: DataX)*/
}
