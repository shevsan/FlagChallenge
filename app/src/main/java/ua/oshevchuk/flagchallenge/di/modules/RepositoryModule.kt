package ua.oshevchuk.flagchallenge.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.oshevchuk.flagchallenge.data.repository.GameResultsRepositoryImpl
import ua.oshevchuk.flagchallenge.domain.repository.GameResultsRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindGameResultsRepository(gameResultsRepositoryImpl: GameResultsRepositoryImpl): GameResultsRepository

}