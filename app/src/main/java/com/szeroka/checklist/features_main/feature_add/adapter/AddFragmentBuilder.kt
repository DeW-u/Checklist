package com.szeroka.checklist.features_main.feature_add.adapter

import android.content.Context
import com.mikepenz.fastadapter.GenericItem

interface AddFragmentBuilder {

    val itemsList: MutableList<GenericItem>

    fun Context.addTripNameField()

    fun Context.addTripDateField()

    fun Context.addCategorySectionTitle()

    fun Context.addSaveButton(
        onClick: () -> Unit
    )
}