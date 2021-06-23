package com.github.dudgns0507.mvvm_cropo.di

import com.github.dudgns0507.mvvm_cropo.data.DataManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Provides
    @Singleton
    fun bindDataManager(): DataManager {
        return DataManager()
    }
}