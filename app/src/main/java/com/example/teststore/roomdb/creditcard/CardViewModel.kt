package com.example.teststore.roomdb.creditcard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CardViewModel @Inject constructor (
    private val cardDao: CardDao,
    private val repository : CardRepo) : ViewModel() {

    val allCards : LiveData<List<CardEntity>>

    init {
        allCards = repository.allCards
    }

    fun insert(cardEntity: CardEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(cardEntity)
    }

    fun delete(cardEntity: CardEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(cardEntity)
    }

    fun update(cardEntity: CardEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(cardEntity)
    }
}