package com.szeroka.checklist.di.modules

import com.szeroka.checklist.utils.Constants.DISPATCHER_DEFAULT_NAME
import com.szeroka.checklist.utils.Constants.DISPATCHER_IO_NAME
import com.szeroka.checklist.utils.Constants.DISPATCHER_UI_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class DispatcherModule {

    @Provides
    @Named(DISPATCHER_UI_NAME)
    fun dispatcherUi(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @Named(DISPATCHER_IO_NAME)
    fun dispatcherIO(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Named(DISPATCHER_DEFAULT_NAME)
    fun dispatcherDefault(): CoroutineDispatcher = Dispatchers.Default
}