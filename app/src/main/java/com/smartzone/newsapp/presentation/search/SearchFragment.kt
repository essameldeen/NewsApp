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
import com.smartzone.newsapp.presentation.home.HomeFragmentDirections
import com.smartzone.newsapp.presentation.home.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment() {
    lateinit var binding: FragmentSearchBinding
    private val viewMode: SearchViewModel by viewModels()
    lateinit var newsAdapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewMode.allList.observe(this@SearchFragment, {
            if (it.articles.size > 0)
                setView(it)
            else {
                showNoData()
            }
        })
        viewMode.showProgress.observe(this@SearchFragment, {
            if (it) {
                showLoading()
            } else {
                dismissLoading()
            }
        })
        viewMode.showErrorMessage.observe(this@SearchFragment, {
            showMessage(it)
        })
    }

    private fun showNoData() {
        binding.noResult.visibility = View.VISIBLE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListenerSearch()
        initAdapter()
        newsAdapter.setOnItemClickListener {
            goToDetailsFragment(it)
        }

    }
    private fun goToDetailsFragment(article: Article) {
        findNavController().navigate(SearchFragmentDirections.navigateToDetailsFragment(article))
    }
    private fun initAdapter() {
        newsAdapter = NewsAdapter()
        binding.rvSearchNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun initListenerSearch() {
        var job: Job? = null
        binding.etSearch.addTextChangedListener { edText ->
            job?.cancel()
            job = MainScope().launch {
                delay(1000L)
                edText?.let {
                    if (edText.toString().isNotEmpty()) {
                        removeAllItemm()
                        viewMode.searchNews(edText.toString())

                    }
                }
            }
        }
    }

    private fun setView(newsData: NewsResponse?) {
        newsAdapter.differ.submitList(newsData!!.articles.toList())
    }

    private fun removeAllItemm() {
        newsAdapter.differ.submitList(listOf())
    }


}