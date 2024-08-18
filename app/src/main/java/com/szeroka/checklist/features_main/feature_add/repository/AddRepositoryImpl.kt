package com.szeroka.checklist.features_main.feature_add.repository

import com.szeroka.checklist.common.domain.TripDomain
import com.szeroka.checklist.data.local.dao.TripDao
import com.szeroka.checklist.data.local.model.entities.TripEntity
import com.szeroka.checklist.utils.Constants.DISPATCHER_IO_NAME
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class AddRepositoryImpl @Inject constructor(
    private val tripDao: TripDao,
    @Named(DISPATCHER_IO_NAME) private val dispatcherIO: CoroutineDispatcher
) : AddRepository {
    override suspend fun addTrip(trip: TripDomain) = withContext(dispatcherIO) {
        tripDao.insert(trip.toDb())
        trip.categories.forEach { category ->
            tripDao.insert(category.toDb(trip))
            category.items.forEach { item ->
                tripDao.insert(item.toDb(category))
            }
        }
    }

}