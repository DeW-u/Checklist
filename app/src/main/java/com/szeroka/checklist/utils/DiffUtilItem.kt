package com.szeroka.checklist.utils

import com.mikepenz.fastadapter.GenericItem

interface DiffUtilItem {

    fun areContentsTheSame(oldItem: GenericItem,
                           newItem: GenericItem): Boolean

    fun areTheSameInstance(oldItem: GenericItem,
                           newItem: GenericItem): Boolean
}