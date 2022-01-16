package com.smartzone.newsapp.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.smartzone.newsapp.data.model.Article
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

        setData(article)
        initListener(article)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       initObserves()

    }


    private fun setData(article: Article) {
        binding.apply {
            author.text = article.author
            title.text = article.title
            content.text = article.content
            description.text = article.description
            publishedAt.text = article.publishedAt
        }
        Glide.with(this).load(article.urlToImage).into(binding.image)
    }
    private fun initListener(article: Article) {
        binding.fav.setOnClickListener {
            viewModel.insertNews(article)
        }
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
    private fun initObserves() {
        viewModel.showErrorMessage.observe(viewLifecycleOwner, {
            showError(it)
        })
        viewModel.showProgress.observe(viewLifecycleOwner, {
            if (it) {
                showLoading()
            } else {
                dismissLoading()
            }
        })
        viewModel.success.observe(viewLifecycleOwner, {
            if (it) {
                showMessage("Article Added Successfully")
            } else {
                showError("An Error Happen Please Try Again")
            }

        })
    }






}