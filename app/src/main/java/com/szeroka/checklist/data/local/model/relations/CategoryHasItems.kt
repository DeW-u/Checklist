package com.szeroka.checklist.data.local.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.szeroka.checklist.data.local.model.entities.CategoryEntity
import com.szeroka.checklist.data.local.model.entities.ItemEntity

data class CategoryHasItems(
    @Embedded
    val category: CategoryEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "categoryId",
        entity = ItemEntity::class
    )
    val items: List<ItemEntity>
)
