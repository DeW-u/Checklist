package com.szeroka.checklist.features_main.feature_add.presentation

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.szeroka.checklist.common.domain.CategoryDomain
import com.szeroka.checklist.common.domain.ItemDomain
import com.szeroka.checklist.common.domain.TripDomain
import com.szeroka.checklist.features_main.feature_add.repository.AddRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.joda.time.DateTime
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val repository: AddRepository
): ViewModel(), DefaultLifecycleObserver {
    private val _trip = MutableStateFlow(TripDomain(id = DateTime.now().millis, name = "", startDate = DateTime.now(), endDate = DateTime.now(), categories = emptyList()))
    val trip = _trip.asStateFlow()

    fun changeTripName(name: String) {
        _trip.getAndUpdate { trip -> trip.copy(name = name) }
    }

    fun changeTripStartDate(startDate: DateTime) {
        _trip.getAndUpdate { trip -> trip.copy(startDate = startDate) }
    }

    fun changeTripEndDate(endDate: DateTime) {
        _trip.getAndUpdate { trip -> trip.copy(endDate = endDate) }
    }

    fun addTripCategory(category: CategoryDomain) {
        _trip.getAndUpdate { trip ->
            val updatedCategories = trip.categories.toMutableList()
            updatedCategories.add(category)

            trip.copy(categories = updatedCategories)
        }
    }

    fun addTripCategoryItem(category: CategoryDomain, item: ItemDomain) {
        _trip.getAndUpdate { trip ->
            val updatedCategories = trip.categories

            updatedCategories.find { it.id == category.id }.let { cat ->
                cat!!.items.toMutableList().add(item)
            }

            trip.copy(categories = updatedCategories)
        }
    }

    fun changeItemCompletionStatus(category: CategoryDomain, item: ItemDomain) {
        _trip.getAndUpdate { trip ->
            val updatedCategories = trip.categories

            updatedCategories.find { it.id == category.id }.let { _category ->
                _category!!.items.find { it.id == item.id }.let { _item ->
                    _item!!.completed = !_item.completed
                }
            }

            trip.copy(categories = updatedCategories)
        }
    }

    fun saveChanges() = viewModelScope.launch {
        repository.addTrip(_trip.value)
    }
}