package com.szeroka.checklist.data.local.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Item",
    indices = [Index(value = ["id"], unique = true)]
)
data class ItemEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "completed") val completed: Boolean,
    @ColumnInfo(name = "categoryId") val categoryId: Long
)
