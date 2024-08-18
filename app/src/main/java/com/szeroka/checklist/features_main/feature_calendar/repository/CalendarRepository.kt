package com.szeroka.checklist.features_main.feature_calendar.repository

import com.szeroka.checklist.common.domain.TripDomain
import kotlinx.coroutines.flow.Flow

interface CalendarRepository {

    fun getTrips(): Flow<List<TripDomain>>
}