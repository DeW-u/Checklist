package com.szeroka.checklist.features_main.feature_archive.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.szeroka.checklist.utils.binding_delegates.dataBinding
import com.szeroka.checklist.R
import com.szeroka.checklist.databinding.FragmentArchiveBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArchiveFragment: Fragment() {

    private val binding: FragmentArchiveBinding by dataBinding(R.layout.fragment_archive)

    private val viewModel: ArchiveViewModel by viewModels()

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