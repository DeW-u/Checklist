package com.szeroka.checklist.data.local.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Trip",
    indices = [Index(value = ["id"], unique = true)]
)
data class TripEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "startDate") val startDate: Long,
    @ColumnInfo(name = "endDate") val endDate: Long,
)
