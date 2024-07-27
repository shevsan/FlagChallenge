package ua.oshevchuk.flagchallenge.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ua.oshevchuk.flagchallenge.data.database.AppDatabase
import ua.oshevchuk.flagchallenge.di.annotations.DefaultDispatcher
import ua.oshevchuk.flagchallenge.di.annotations.IoDispatcher
import ua.oshevchuk.flagchallenge.di.annotations.MainDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, AppDatabase::class.java, "game_results"
    ).build()

    @Singleton
    @Provides
    fun provideGameResultsDao(db: AppDatabase) = db.gameResultsDao()

    @Provides
    @DefaultDispatcher
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @IoDispatcher
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @MainDispatcher
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}