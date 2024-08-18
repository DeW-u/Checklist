package com.szeroka.checklist.features_main.feature_add.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.szeroka.checklist.R
import com.szeroka.checklist.databinding.ItemAddTripTextFieldBinding
import com.szeroka.checklist.utils.DiffUtilItem

class AddTripTextFieldItem(
    private val hint: String,
    private val onTextChanged: (String) -> Unit
) : AbstractBindingItem<ItemAddTripTextFieldBinding>(), DiffUtilItem {

    override val type: Int = R.id.add_trip_text_field_item_id
    override var identifier: Long = hint.hashCode().toLong()

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ItemAddTripTextFieldBinding = ItemAddTripTextFieldBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ItemAddTripTextFieldBinding, payloads: List<Any>) {
        binding.apply {
            textFieldHint = hint
            textFieldValue = ""
            editText.doOnTextChanged { text, _, _, _ ->
                onTextChanged(text.toString())
            }
        }
    }

    override fun areContentsTheSame(oldItem: GenericItem, newItem: GenericItem): Boolean =
        (oldItem as AddTripTextFieldItem).identifier == (newItem as AddTripTextFieldItem).identifier

    override fun areTheSameInstance(oldItem: GenericItem, newItem: GenericItem): Boolean =
        oldItem is AddTripTextFieldItem && newItem is AddTripTextFieldItem
}