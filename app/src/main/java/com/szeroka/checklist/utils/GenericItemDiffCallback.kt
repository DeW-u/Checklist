package com.szeroka.checklist.utils

import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.diff.DiffCallback

class GenericItemDiffCallback : DiffCallback<GenericItem> {

    override fun areContentsTheSame(oldItem: GenericItem,
                                    newItem: GenericItem): Boolean = when (oldItem) {
        is DiffUtilItem -> oldItem.areTheSameInstance(oldItem, newItem) && oldItem.areContentsTheSame(oldItem, newItem)
        else -> false
    }

    override fun areItemsTheSame(oldItem: GenericItem,
                                 newItem: GenericItem): Boolean =
        when (oldItem) {
            else -> oldItem == newItem
        }


    override fun getChangePayload(oldItem: GenericItem,
                                  oldItemPosition: Int,
                                  newItem: GenericItem,
                                  newItemPosition: Int): Any? = newItem

}