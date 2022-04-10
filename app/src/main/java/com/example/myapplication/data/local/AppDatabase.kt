package com.example.myapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.data.entitiy.CartDataEntity
import com.example.myapplication.data.entitiy.DataX

/**
 * To manage data items that can be accessed, updated
 * & maintain relationships between them
 *
 * @Created by NADA
 */
@Database(entities = [DataX::class,CartDataEntity::class], version = 3, exportSchema = true)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

  //  abstract val roomDao: RoomDao
    abstract fun getRoomDao(): RoomDao

    companion object {
        const val DB_NAME = "CommericDatabase.db"
    }
}
