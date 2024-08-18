package com.szeroka.checklist.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.szeroka.checklist.data.local.model.entities.CategoryEntity
import com.szeroka.checklist.data.local.model.entities.ItemEntity
import com.szeroka.checklist.data.local.model.entities.TripEntity
import com.szeroka.checklist.data.local.model.relations.TripHasCategories
import kotlinx.coroutines.flow.Flow

@Dao
interface TripDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg trip: TripEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg category: CategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg item: ItemEntity)

    @Query("SELECT * FROM Trip")
    fun flowTrips(): Flow<List<TripHasCategories>>

    @Query("SELECT * FROM Trip WHERE id = :id")
    fun flowTrip(id: Long): Flow<TripHasCategories>
}