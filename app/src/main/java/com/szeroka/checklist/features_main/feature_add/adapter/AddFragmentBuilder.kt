package com.szeroka.checklist.features_main.feature_add.adapter

import android.content.Context
import com.mikepenz.fastadapter.GenericItem
import com.szeroka.checklist.common.domain.CategoryDomain

interface AddFragmentBuilder {

    val itemsList: MutableList<GenericItem>
    val categoriesList: MutableList<GenericItem>

    fun Context.addTripNameFieldItem(
        sectionName: String,
        hint: String,
        onTextChanged: (String) -> Unit
    )

    fun Context.addTripDateFieldItem(
        sectionName: String,
        hint: String,
        date: String?,
        onClick: () -> Unit
    )

    fun Context.addCategoryItem(
        category: CategoryDomain?,
        onClick: () -> Unit
    )
}