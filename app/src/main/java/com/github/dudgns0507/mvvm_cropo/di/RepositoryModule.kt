package com.github.dudgns0507.mvvm_cropo.di

import com.github.dudgns0507.mvvm_cropo.data.repository.DataRepository
import com.github.dudgns0507.mvvm_cropo.data.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@InstallIn(ActivityComponent::class)
@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun bindDataRepository(apiService: ApiService): DataRepository {
        return DataRepository(apiService)
    }
}