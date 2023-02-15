package com.example.teststore.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.teststore.api.RetrofitApi
import com.example.teststore.repository.CartRepo
import com.example.teststore.roomdb.AppDatabase
import com.example.teststore.roomdb.ProductDao
import com.example.teststore.roomdb.creditcard.CardDao
import com.example.teststore.roomdb.creditcard.CardRepo
import com.example.teststore.util.Util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRoomDatabase (@ApplicationContext context : Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "app_database",
        ).allowMainThreadQueries()
        .build()

    @Singleton
    @Provides
    fun injectDao(database : AppDatabase) = database.productDao()

    @Singleton
    @Provides
    fun injectRetrofitApi() : RetrofitApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(RetrofitApi::class.java)
    }

    @Singleton
    @Provides
    fun injectRepository(retrofitApi: RetrofitApi, dao : ProductDao) = CartRepo(dao,retrofitApi)

    @Singleton
    @Provides
    fun injectCardDao(database: AppDatabase) = database.cardDao()

    @Singleton
    @Provides
    fun injectCardRepo(dao : CardDao) = CardRepo(dao)
}