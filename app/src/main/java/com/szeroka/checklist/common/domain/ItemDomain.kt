package com.szeroka.checklist.common.domain

import com.szeroka.checklist.data.local.model.entities.ItemEntity

data class ItemDomain(
    val id: Long,
    val name: String,
    var completed: Boolean
) {
    companion object {
        fun fromDb(entity: ItemEntity): ItemDomain = ItemDomain(
            id = entity.id,
            name = entity.name,
            completed = entity.completed
        )
    }

    fun toDb(category: CategoryDomain): ItemEntity = ItemEntity(
        id = this.id,
        name = this.name,
        completed = this.completed,
        categoryId = category.id
    )
}
