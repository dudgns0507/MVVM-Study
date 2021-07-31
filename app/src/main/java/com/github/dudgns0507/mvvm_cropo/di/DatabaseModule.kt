package com.github.dudgns0507.mvvm_cropo.di

import android.content.Context
import androidx.room.Room
import com.github.dudgns0507.mvvm_cropo.data.local.AppDatabase
import com.github.dudgns0507.mvvm_cropo.data.local.UserItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            context.packageName.toString()
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): UserItemDao {
        return appDatabase.userItemDao()
    }
}