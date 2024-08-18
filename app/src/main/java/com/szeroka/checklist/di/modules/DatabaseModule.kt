package com.szeroka.checklist.di.modules

import android.content.Context
import androidx.room.Room
import com.szeroka.checklist.data.local.core_database.CoreDatabase
import com.szeroka.checklist.data.local.dao.TripDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideTripDao(database: CoreDatabase): TripDao {
        return database.tripDao()
    }

    @Provides
    fun provideCoreDatabase(@ApplicationContext appContext: Context): CoreDatabase {
        return Room.databaseBuilder(
            appContext,
            CoreDatabase::class.java,
            "CoreDB"
        ).build()
    }
}