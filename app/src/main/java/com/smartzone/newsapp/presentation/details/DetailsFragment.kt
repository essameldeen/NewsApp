package com.smartzone.newsapp.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smartzone.newsapp.databinding.FragmentDetailsBinding
import com.smartzone.newsapp.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint

class DetailsFragment : BaseFragment() {
    lateinit var binding: FragmentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

}