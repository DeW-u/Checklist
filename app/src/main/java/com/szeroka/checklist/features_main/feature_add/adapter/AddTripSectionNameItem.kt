package com.szeroka.checklist.features_main.feature_add.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.szeroka.checklist.R
import com.szeroka.checklist.databinding.ItemAddTripSectionNameBinding
import com.szeroka.checklist.utils.DiffUtilItem

class AddTripSectionNameItem (
    private val name: String
) : AbstractBindingItem<ItemAddTripSectionNameBinding>(), DiffUtilItem {

    override val type: Int = R.id.add_trip_section_name_item_id
    override var identifier: Long = name.hashCode().toLong()

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ItemAddTripSectionNameBinding = ItemAddTripSectionNameBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ItemAddTripSectionNameBinding, payloads: List<Any>) {
        binding.apply {
            sectionTitle = name
        }
    }

    override fun areContentsTheSame(oldItem: GenericItem, newItem: GenericItem): Boolean =
        (oldItem as AddTripSectionNameItem).name == (newItem as AddTripSectionNameItem).name

    override fun areTheSameInstance(oldItem: GenericItem, newItem: GenericItem): Boolean =
        oldItem is AddTripSectionNameItem && newItem is AddTripSectionNameItem
}