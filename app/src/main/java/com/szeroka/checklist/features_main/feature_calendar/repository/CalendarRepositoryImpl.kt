package com.szeroka.checklist.features_main.feature_calendar.repository

import com.szeroka.checklist.common.domain.TripDomain
import com.szeroka.checklist.data.local.dao.TripDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CalendarRepositoryImpl @Inject constructor(
    private val tripDao: TripDao,
) : CalendarRepository {

    override fun getTrips(): Flow<List<TripDomain>> =
        tripDao.flowTrips().map { it.map { trip -> TripDomain.fromDb(trip) } }.distinctUntilChanged()
}