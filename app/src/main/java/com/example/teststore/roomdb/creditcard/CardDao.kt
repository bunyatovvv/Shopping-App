package com.example.teststore.roomdb.creditcard

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface CardDao {

    @Query("SELECT * FROM card_list order by id asc")
    fun getAll() : LiveData<List<CardEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg card : CardEntity)

    @Delete
    suspend fun delete(card : CardEntity)

    @Update
    suspend fun update(vararg card : CardEntity)
}