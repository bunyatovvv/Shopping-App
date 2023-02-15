package com.example.teststore.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.teststore.roomdb.creditcard.CardDao
import com.example.teststore.roomdb.creditcard.CardEntity


@Database(entities = [ProductEntity::class, CardEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao() : ProductDao
    abstract fun cardDao() : CardDao

}