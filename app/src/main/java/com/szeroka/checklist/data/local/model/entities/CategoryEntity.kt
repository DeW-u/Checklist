package com.szeroka.checklist.data.local.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Category",
    indices = [Index(value = ["id"], unique = true)]
)
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "iconId") val iconId: Int,
    @ColumnInfo(name = "tripId") val tripId: Long
)
