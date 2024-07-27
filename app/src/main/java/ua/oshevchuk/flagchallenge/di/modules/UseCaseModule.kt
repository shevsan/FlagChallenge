package ua.oshevchuk.flagchallenge.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.oshevchuk.flagchallenge.domain.usecase.implementations.GetLastGameUseCaseImpl
import ua.oshevchuk.flagchallenge.domain.usecase.implementations.SaveGameResultUseCaseImpl
import ua.oshevchuk.flagchallenge.domain.usecase.interfaces.GetLastGameUseCase
import ua.oshevchuk.flagchallenge.domain.usecase.interfaces.SaveGameResultUseCase

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindGetLastGameUseCase(getLastGameUseCaseImpl: GetLastGameUseCaseImpl): GetLastGameUseCase

    @Binds
    abstract fun bindSaveGameResultUseCase(saveGameResultUseCaseImpl: SaveGameResultUseCaseImpl): SaveGameResultUseCase

}