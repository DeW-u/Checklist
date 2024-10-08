package com.szeroka.checklist.common.domain

import com.szeroka.checklist.data.local.model.entities.CategoryEntity
import com.szeroka.checklist.data.local.model.relations.CategoryHasItems

data class CategoryDomain(
    val id: Long,
    val name: String,
    val iconId: Int,
    val items: List<ItemDomain>
) {
    companion object {
        fun fromDb(entity: CategoryHasItems): CategoryDomain = CategoryDomain(
            id = entity.category.id,
            name = entity.category.name,
            iconId = entity.category.iconId,
            items = entity.items.map { ItemDomain.fromDb(it) }
        )
    }

    fun toDb(trip: TripDomain): CategoryEntity = CategoryEntity(
        id = this.id,
        name = this.name,
        iconId = this.iconId,
        tripId = trip.id
    )
}
