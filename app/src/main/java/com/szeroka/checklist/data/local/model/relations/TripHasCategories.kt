package com.szeroka.checklist.data.local.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.szeroka.checklist.data.local.model.entities.CategoryEntity
import com.szeroka.checklist.data.local.model.entities.TripEntity

data class TripHasCategories(
    @Embedded
    val trip: TripEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "tripId",
        entity = CategoryEntity::class
    )
    val categories: List<CategoryHasItems>
)
