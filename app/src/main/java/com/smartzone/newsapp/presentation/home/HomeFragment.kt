package com.smartzone.newsapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.smartzone.newsapp.data.model.Article
import com.smartzone.newsapp.data.model.NewsResponse
import com.smartzone.newsapp.databinding.FragmentHomeBinding
import com.smartzone.newsapp.presentation.base.BaseFragment
import com.smartzone.newsapp.presentation.base.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initView()
        initListener()


    }
    override fun onResume() {
        super.onResume()
        viewModel.getAllNews()
    }



    private fun initObservers() {
        viewModel.allNews.observe(viewLifecycleOwner, {
            setDataInView(it)
        })
        viewModel.showProgress.observe(viewLifecycleOwner, {
            if (it) {
                showLoading()
            } else {
                dismissLoading()
            }
        })
        viewModel.showErrorMessage.observe(viewLifecycleOwner, {
            showError(it)
        })
    }
    private fun initView() {
        newsAdapter = NewsAdapter()
        binding.rvAllNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
    private fun initListener() {
        newsAdapter.setOnItemClickListener {
            goToDetailsFragment(it)
        }
    }
    private fun setDataInView(newsData: NewsResponse?) {
        newsAdapter.differ.submitList(newsData!!.articles.toList())
    }
    private fun goToDetailsFragment(article: Article) {
        findNavController().navigate(HomeFragmentDirections.navigateToDetailsFragment(article))
    }





}