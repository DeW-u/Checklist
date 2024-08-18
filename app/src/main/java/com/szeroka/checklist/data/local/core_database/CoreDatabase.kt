package com.szeroka.checklist.data.local.core_database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.szeroka.checklist.data.local.dao.TripDao
import com.szeroka.checklist.data.local.model.entities.CategoryEntity
import com.szeroka.checklist.data.local.model.entities.ItemEntity
import com.szeroka.checklist.data.local.model.entities.TripEntity

@Database(entities = [TripEntity::class, CategoryEntity::class, ItemEntity::class], version = 1)
abstract class CoreDatabase: RoomDatabase() {

    abstract fun tripDao(): TripDao
}