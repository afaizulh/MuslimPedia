package com.faizul.muslimpedia.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.faizul.muslimpedia.adapter.NewsAdapter
import com.faizul.muslimpedia.data.repository.NewsRepository
import com.faizul.muslimpedia.databinding.FragmentAboutQuranBinding
import com.faizul.muslimpedia.ui.NewsViewModel
import com.faizul.muslimpedia.utils.NewsViewModelFactory

class AboutQuranFragment : Fragment() {
    lateinit var binding: FragmentAboutQuranBinding
    private val commonNewsViewModel: NewsViewModel by viewModels {
        NewsViewModelFactory(NewsRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAboutQuranBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mAdapter = NewsAdapter()

        if (commonNewsViewModel.aboutAlQuranNews.value == null){
            commonNewsViewModel.getAboutAlQuranNews()
        }

        commonNewsViewModel.aboutAlQuranNews.observe(viewLifecycleOwner) {dataNews ->
            mAdapter.setData(dataNews.articles)
            binding.rvAboutQuranNews.apply {
                adapter = mAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
        commonNewsViewModel.isLoading.observe(viewLifecycleOwner) {
            isLoading(it)
        }
    }

    private fun isLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.loadingView.root.visibility = View.VISIBLE
        } else {
            binding.loadingView.root.visibility = View.GONE
        }
    }
}