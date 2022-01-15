package com.smartzone.newsapp.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.smartzone.newsapp.databinding.FragmentDetailsBinding
import com.smartzone.newsapp.presentation.base.BaseFragment
import com.smartzone.newsapp.presentation.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.article_item_rv.view.*

@AndroidEntryPoint
class DetailsFragment : BaseFragment() {
    lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()
    private val args: DetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val article = args.article

        binding.author.text = article.author
        binding.title.text = article.title
        binding.content.text = article.content
        binding.description.text = article.description
        binding.publishedAt.text = article.publishedAt
        Glide.with(this).load(article.urlToImage).into(binding.image)

        binding.fav.setOnClickListener {
            viewModel.insertNews(article)
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.showErrorMessage.observe(this@DetailsFragment, {
            showError(it)
        })
        viewModel.showProgress.observe(this@DetailsFragment, {
            if (it) {
                showLoading()
            } else {
                dismissLoading()
            }
        })
        viewModel.success.observe(this@DetailsFragment, {
            if (it) {
                showMessage("Article Added Successfully")
            } else {
                showError("An Error Happen Please Try Again")
            }

        })
    }


}