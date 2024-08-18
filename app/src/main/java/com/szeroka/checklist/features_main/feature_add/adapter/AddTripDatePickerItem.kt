package com.szeroka.checklist.features_main.feature_add.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.szeroka.checklist.R
import com.szeroka.checklist.databinding.ItemAddTripTextFieldBinding
import com.szeroka.checklist.utils.DiffUtilItem
import com.szeroka.checklist.utils.setSafeOnClickListener

class AddTripDatePickerItem(
    private val hint: String,
    private val date: String?,
    private val onClick: () -> Unit,
) : AbstractBindingItem<ItemAddTripTextFieldBinding>(), DiffUtilItem {

    override val type: Int = R.id.add_trip_date_picker_item_id
    override var identifier: Long = date.hashCode().toLong()

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ItemAddTripTextFieldBinding = ItemAddTripTextFieldBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ItemAddTripTextFieldBinding, payloads: List<Any>) {
        binding.apply {
            editText.isFocusable = false
            editText.isClickable = true
            textFieldHint = hint
            textFieldValue = date ?: ""
            editText.setSafeOnClickListener {
                onClick()
            }
        }
    }

    override fun areContentsTheSame(oldItem: GenericItem, newItem: GenericItem): Boolean =
        (oldItem as AddTripDatePickerItem).identifier == (newItem as AddTripDatePickerItem).identifier

    override fun areTheSameInstance(oldItem: GenericItem, newItem: GenericItem): Boolean =
        oldItem is AddTripDatePickerItem && newItem is AddTripDatePickerItem
}