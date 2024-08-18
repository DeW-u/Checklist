package com.szeroka.checklist.features_main.feature_calendar.presentation

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.szeroka.checklist.features_main.feature_calendar.repository.CalendarRepository
import com.szeroka.checklist.utils.Constants.DISPATCHER_IO_NAME
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val repository: CalendarRepository,
    @Named(DISPATCHER_IO_NAME) private val dispatcherIO: CoroutineDispatcher
) : ViewModel(), DefaultLifecycleObserver {

    val tripsFlow = repository.getTrips().flowOn(dispatcherIO)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
}