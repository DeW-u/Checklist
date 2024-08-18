package com.szeroka.checklist.features_main.feature_add.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.szeroka.checklist.R
import com.szeroka.checklist.common.domain.CategoryDomain
import com.szeroka.checklist.databinding.ItemAddTripCategoryCardBinding
import com.szeroka.checklist.utils.DiffUtilItem
import com.szeroka.checklist.utils.setSafeOnClickListener

class AddTripCategoryItem(
    private val category: CategoryDomain?,
    private val onClick: () -> Unit
) : AbstractBindingItem<ItemAddTripCategoryCardBinding>(), DiffUtilItem {

    override val type: Int = R.id.add_trip_category_item_id
    override var identifier: Long = category?.id ?: R.id.add_trip_category_item_id.toLong()

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ItemAddTripCategoryCardBinding = ItemAddTripCategoryCardBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ItemAddTripCategoryCardBinding, payloads: List<Any>) {
        binding.apply {
            category?.let {
                imageView.setImageResource(category.iconId)
                textView.text = category.name
            } ?: run {
                imageView.setImageResource(R.drawable.ic_add)
                textView.text = binding.root.context.getString(R.string.trip_add_category_add)
                cardView.setSafeOnClickListener {
                    onClick()
                }
            }
        }
    }

    override fun areContentsTheSame(oldItem: GenericItem, newItem: GenericItem): Boolean =
        (oldItem as AddTripCategoryItem).category == (newItem as AddTripCategoryItem).category

    override fun areTheSameInstance(oldItem: GenericItem, newItem: GenericItem): Boolean =
        oldItem is AddTripCategoryItem && newItem is AddTripCategoryItem
}