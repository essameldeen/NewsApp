package com.smartzone.newsapp.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.smartzone.newsapp.data.model.Article
import com.smartzone.newsapp.data.model.NewsResponse
import com.smartzone.newsapp.databinding.FragmentSearchBinding
import com.smartzone.newsapp.presentation.base.BaseFragment
import com.smartzone.newsapp.presentation.base.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment() {
    private lateinit var binding: FragmentSearchBinding
    private val viewMode: SearchViewModel by viewModels()
    private lateinit var newsAdapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserves()
        initListenerSearch()
        initAdapter()
        initListeners()


    }

    private fun initObserves() {
        viewMode.allNews.observe(viewLifecycleOwner, {
            if (it.articles.size > 0)
                setDataInView(it)
            else {
                showNoData()
            }
        })
        viewMode.showProgress.observe(viewLifecycleOwner, {
            if (it) {
                showLoading()
            } else {
                dismissLoading()
            }
        })
        viewMode.showErrorMessage.observe(viewLifecycleOwner, {
            showMessage(it)
        })
    }

    private fun initListenerSearch() {
        var job: Job? = null
        binding.etSearch.addTextChangedListener { edText ->
            job?.cancel()
            job = MainScope().launch {
                delay(1000L)
                edText?.let {
                    if (edText.toString().isNotEmpty()) {
                        removeAllItem()
                        viewMode.searchNews(edText.toString())

                    }
                }
            }
        }
    }

    private fun initAdapter() {
        newsAdapter = NewsAdapter()
        binding.rvSearchNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun initListeners() {
        newsAdapter.setOnItemClickListener {
            goToDetailsFragment(it)
        }
    }

    private fun setDataInView(newsData: NewsResponse?) {
        newsAdapter.differ.submitList(newsData!!.articles.toList())
    }

    private fun removeAllItem() {
        newsAdapter.differ.submitList(listOf())
    }

    private fun showNoData() {
        binding.noResult.visibility = View.VISIBLE
    }

    private fun goToDetailsFragment(article: Article) {
        findNavController().navigate(SearchFragmentDirections.navigateToDetailsFragment(article))
    }


}