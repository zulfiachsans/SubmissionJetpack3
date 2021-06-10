package com.dicoding.submissionjetpack2.ui.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.submissionjetpack2.data.source.local.TvEntity
import com.dicoding.submissionjetpack2.databinding.FragmentTvBinding
import com.dicoding.submissionjetpack2.ui.detail.DetailActivity
import com.dicoding.submissionjetpack2.ui.detail.DetailViewModel.Companion.TV_SHOWS
import com.dicoding.submissionjetpack2.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_tv.*

class TvFragment : Fragment(), TvAdapter.OnItemClickCallback {
    private lateinit var fragmentTvBinding : FragmentTvBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentTvBinding = FragmentTvBinding.inflate(layoutInflater,container,false)
        return fragmentTvBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            showProgressBar(true)

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvViewModel::class.java]
            val tvShowAdapter = TvAdapter(this)

            viewModel.getTv().observe(viewLifecycleOwner, {tvShows ->
                showProgressBar(false)
                tvShowAdapter.setTvShows(tvShows)
                tvShowAdapter.setOnItemClickCallback(this)
                tvShowAdapter.notifyDataSetChanged()
            })

            with(fragmentTvBinding.rvTv) {
                layoutManager = GridLayoutManager(context,2)
                setHasFixedSize(true)
                this.adapter = tvShowAdapter
            }
        }
    }
    private fun showProgressBar(state: Boolean) {
        fragmentTvBinding.progressTvShows.isVisible = state
        fragmentTvBinding.rvTv.isInvisible = state
    }
    override fun onItemClicked(id: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_TYPE, id)
        intent.putExtra(DetailActivity.EXTRA_CATEGORY, TV_SHOWS)
        context?.startActivity(intent)
    }

    private fun setRecycler(data: List<TvEntity>) {
        rv_tv.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = TvAdapter(this@TvFragment)
        }.also {
            it.adapter.let { adapter ->
                when (adapter) {
                    is TvAdapter -> {
                        adapter.setTvShows(data)
                    }
                }
            }
        }
    }
}