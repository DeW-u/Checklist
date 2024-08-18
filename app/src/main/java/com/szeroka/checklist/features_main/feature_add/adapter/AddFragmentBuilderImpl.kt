package com.szeroka.checklist.features_main.feature_add.adapter

import android.content.Context
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.mikepenz.fastadapter.GenericItem
import com.szeroka.checklist.common.domain.CategoryDomain

object AddFragmentBuilderImpl : AddFragmentBuilder, DefaultLifecycleObserver {

    override val itemsList = mutableListOf<GenericItem>()
    override val categoriesList = mutableListOf<GenericItem>()

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        itemsList.clear()
        categoriesList.clear()
    }

    override fun Context.addTripNameFieldItem(sectionName: String, hint: String, onTextChanged: (String) -> Unit) {
        itemsList.add(AddTripSectionNameItem(name = sectionName))
        itemsList.add(AddTripTextFieldItem(hint = hint, onTextChanged = onTextChanged))
    }

    override fun Context.addTripDateFieldItem(
        sectionName: String,
        hint: String,
        date: String?,
        onClick: () -> Unit
    ) {
        itemsList.add(AddTripSectionNameItem(name = sectionName))
        itemsList.add(AddTripDatePickerItem(hint = hint, date = date, onClick = onClick))
    }

    override fun Context.addCategoryItem(category: CategoryDomain?, onClick: () -> Unit) {
        categoriesList.add(AddTripCategoryItem(category = category, onClick = onClick))
    }
}