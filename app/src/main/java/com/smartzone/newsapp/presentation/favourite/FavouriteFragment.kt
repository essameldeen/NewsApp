package com.smartzone.newsapp.presentation.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smartzone.newsapp.databinding.FragmentDetailsBinding
import com.smartzone.newsapp.databinding.FragmentFavouriteBinding
import com.smartzone.newsapp.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint

class FavouriteFragment : BaseFragment() {
    lateinit var binding: FragmentFavouriteBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

}