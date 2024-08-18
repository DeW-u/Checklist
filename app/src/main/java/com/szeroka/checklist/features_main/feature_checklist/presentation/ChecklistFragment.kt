package com.szeroka.checklist.features_main.feature_checklist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.szeroka.checklist.utils.binding_delegates.dataBinding
import com.szeroka.checklist.R
import com.szeroka.checklist.databinding.FragmentChecklistBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChecklistFragment: Fragment() {

    private val binding: FragmentChecklistBinding by dataBinding(R.layout.fragment_checklist)

    private val viewModel: ChecklistViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}