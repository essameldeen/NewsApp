package com.smartzone.newsapp.presentation.favourite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.smartzone.newsapp.data.model.Article
import com.smartzone.newsapp.databinding.FragmentFavouriteBinding
import com.smartzone.newsapp.presentation.base.BaseFragment
import com.smartzone.newsapp.presentation.home.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favourite.*

@AndroidEntryPoint
class FavouriteFragment : BaseFragment() {
    private lateinit var binding: FragmentFavouriteBinding
    private val viewModel: FavouriteViewModel by viewModels()
    private lateinit var adapters: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.showErrorMessage.observe(this@FavouriteFragment, {
            showMessage(it)
        })
        viewModel.success.observe(this@FavouriteFragment, {
            if (it) {
                showMessage("Deleted Succeed")
            } else {
                showError("An Error Happened! ")
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllNewsDb().observe(viewLifecycleOwner, {
            if (it.isEmpty()) {
                binding.noData.visibility = View.VISIBLE

            } else {
                setView(it)
            }
        })

        initView()

        adapters.setOnItemClickListener {
            goToDetailsFragment(it)
        }
        swapForDelete()


    }

    private fun swapForDelete() {
        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT

        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = adapters.differ.currentList[position]
                viewModel.removeNews(article)

            }


        }
        ItemTouchHelper(itemTouchHelper).apply {
            attachToRecyclerView(binding.rvSaveNews)
        }
    }

    private fun goToDetailsFragment(article: Article) {
        findNavController().navigate(FavouriteFragmentDirections.navigateToDetailsFragment(article))
    }

    private fun initView() {
        adapters = NewsAdapter()
        binding.rvSaveNews.apply {
            adapter = adapters
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun setView(newsData: List<Article>) {
        adapters.differ.submitList(newsData)
    }

}