package com.szeroka.checklist.features_main.feature_checklist.presentation

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChecklistViewModel @Inject constructor(): ViewModel(), DefaultLifecycleObserver {
}