package com.example.teststore.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductDao {

    @Query("SELECT * FROM cart_items order by id desc")
    fun getAll() : LiveData<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg product : ProductEntity)

    @Delete
    suspend fun delete(product : ProductEntity)

    @Update
    suspend fun update(vararg product : ProductEntity)
}