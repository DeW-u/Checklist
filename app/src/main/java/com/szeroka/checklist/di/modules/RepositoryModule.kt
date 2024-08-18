package com.szeroka.checklist.di.modules

import com.szeroka.checklist.features_main.feature_add.repository.AddRepository
import com.szeroka.checklist.features_main.feature_add.repository.AddRepositoryImpl
import com.szeroka.checklist.features_main.feature_calendar.repository.CalendarRepository
import com.szeroka.checklist.features_main.feature_calendar.repository.CalendarRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCalendarRepository(impl: CalendarRepositoryImpl): CalendarRepository

    @Binds
    abstract fun bindAddRepository(impl: AddRepositoryImpl): AddRepository
}