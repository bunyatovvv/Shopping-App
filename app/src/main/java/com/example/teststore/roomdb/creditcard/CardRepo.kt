package com.example.teststore.roomdb.creditcard

import androidx.lifecycle.LiveData
import javax.inject.Inject


class CardRepo @Inject constructor(private val repo : CardDao) {

    val allCards : LiveData<List<CardEntity>> = repo.getAll()

    suspend fun insert(cardEntity: CardEntity){
        repo.insert(cardEntity)
    }

    suspend fun delete(cardEntity: CardEntity){
        repo.delete(cardEntity)
    }

    suspend fun update(cardEntity: CardEntity){
        repo.update(cardEntity)
    }
}

