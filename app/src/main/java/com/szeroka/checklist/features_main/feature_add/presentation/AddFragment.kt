package com.szeroka.checklist.features_main.feature_add.presentation

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import com.szeroka.checklist.utils.binding_delegates.dataBinding
import com.szeroka.checklist.R
import com.szeroka.checklist.common.domain.TripDomain
import com.szeroka.checklist.databinding.FragmentAddBinding
import com.szeroka.checklist.features_main.feature_add.adapter.AddFragmentBuilder
import com.szeroka.checklist.features_main.feature_add.adapter.AddFragmentBuilderImpl
import com.szeroka.checklist.utils.Constants
import com.szeroka.checklist.utils.GenericItemDiffCallback
import com.szeroka.checklist.utils.collectFlow
import dagger.hilt.android.AndroidEntryPoint
import org.joda.time.DateTime

@AndroidEntryPoint
class AddFragment: Fragment(), AddFragmentBuilder by AddFragmentBuilderImpl {

    private val binding: FragmentAddBinding by dataBinding(R.layout.fragment_add)

    private val viewModel: AddViewModel by viewModels()

    private val fieldsAdapter: ItemAdapter<GenericItem> = ItemAdapter()
    private val categoriesAdapter: ItemAdapter<GenericItem> = ItemAdapter()

    private var currentDatePicker: MaterialDatePicker<Pair<Long, Long>> = MaterialDatePicker()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.apply {
            adapter = FastAdapter.with(fieldsAdapter)
            adapterCategories = FastAdapter.with(categoriesAdapter)

            recyclerViewTripCategories.layoutManager = GridLayoutManager(
                requireContext(), 4, GridLayoutManager.VERTICAL, false)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeTripChanges()
    }

    private fun observeTripChanges() = collectFlow(viewModel.trip) {
        Log.d("dupa", it.name + " " + it.startDate)
        Log.d("dupa2", it.categories.count().toString())
        showTripFields(it)
        showCategories(it)
    }

    private fun showTripFields(trip: TripDomain) {
        itemsList.clear()
        FastAdapterDiffUtil.set(
            fieldsAdapter,
            buildFields(trip),
            GenericItemDiffCallback()
        )
    }

    private fun showCategories(trip: TripDomain) {
        categoriesList.clear()
        FastAdapterDiffUtil.set(
            categoriesAdapter,
            buildCategories(trip),
            GenericItemDiffCallback()
        )
    }

    private fun buildFields(trip: TripDomain): List<GenericItem> {
        requireContext().apply {
            addTripNameFieldItem(sectionName = getString(R.string.trip_add_name_section), hint = getString(R.string.trip_add_name_hint)) {
                viewModel.changeTripName(it)
            }
            addTripDateFieldItem(
                sectionName = getString(R.string.trip_add_date_section),
                hint = getString(R.string.trip_add_date_hint),
                date = "${trip.startDate.toString("dd.MM.yyyy")} -> ${trip.endDate.toString("dd.MM.yyyy")}"
            ) {
                if (currentDatePicker.isVisible.not()) {
                    currentDatePicker = createDatePickerDialog()

                    currentDatePicker.show(parentFragmentManager, Constants.DATEPICKER_TAG)
                }
            }
        }

        return itemsList
    }

    private fun buildCategories(trip: TripDomain): List<GenericItem> {
        requireContext().apply {
            if (trip.categories.isEmpty()) {
                Log.d("dupa 3", "hello")
                addCategoryItem(null) {

                }
            } else {
                trip.categories.forEach { category ->
                    addCategoryItem(category) {}
                }
            }
        }

        return categoriesList
    }

    private fun createDatePickerDialog(): MaterialDatePicker<Pair<Long, Long>> {
        val picker = MaterialDatePicker.Builder.dateRangePicker()
            .setTitleText(R.string.trip_add_date_hint)
            .setSelection(Pair(DateTime.now().millis, DateTime.now().plusDays(1).millis))
            .setPositiveButtonText(R.string.ok)
            .setNegativeButtonText(R.string.cancel)
            .build()

        picker.addOnPositiveButtonClickListener {
            viewModel.changeTripStartDate(DateTime(it.first))
            viewModel.changeTripEndDate(DateTime(it.second))
        }

        return picker
    }
}