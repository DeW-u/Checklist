package com.szeroka.checklist.common.domain

import androidx.room.ColumnInfo
import com.szeroka.checklist.data.local.model.entities.TripEntity
import com.szeroka.checklist.data.local.model.relations.TripHasCategories
import org.joda.time.DateTime

data class TripDomain(
    val id: Long,
    val name: String,
    val startDate: DateTime,
    val endDate: DateTime,
    val categories: List<CategoryDomain>
) {
    companion object {
        fun fromDb(entity: TripHasCategories): TripDomain = TripDomain(
            id = entity.trip.id,
            name = entity.trip.name,
            startDate = DateTime(entity.trip.startDate),
            endDate = DateTime(entity.trip.endDate),
            categories = entity.categories.map { CategoryDomain.fromDb(it) }
        )
    }

    fun toDb(): TripEntity = TripEntity(
        id = this.id,
        name = this.name,
        startDate = this.startDate.millis,
        endDate = this.endDate.millis
    )

    fun calculateCompletion(): Double {
        var numberOfItems: Double = 0.0
        var numberOfCompletedItems: Double = 0.0

        categories.forEach { category ->
            category.items.forEach { item ->
                numberOfItems += 1
                if (item.completed) numberOfCompletedItems += 1
            }
        }

        return  numberOfCompletedItems/numberOfItems
    }
}
