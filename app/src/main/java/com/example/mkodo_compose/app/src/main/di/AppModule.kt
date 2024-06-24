package com.example.mkodo_compose.app.src.main.di

import com.example.mkodo_compose.app.src.main.repository.LotteryDrawsRepository
import com.example.mkodo_compose.app.src.main.repository.LotteryDrawsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRepository(): LotteryDrawsRepository {
        return LotteryDrawsRepositoryImpl()
    }
}
