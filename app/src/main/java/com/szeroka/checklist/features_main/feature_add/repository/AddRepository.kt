package com.szeroka.checklist.features_main.feature_add.repository

import com.szeroka.checklist.common.domain.TripDomain

interface AddRepository {

    suspend fun addTrip(trip: TripDomain)
}