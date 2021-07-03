package com.dicoding.submissionjetpack3.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.submissionjetpack3.R
import com.dicoding.submissionjetpack3.databinding.FragmentTvBinding
import com.dicoding.submissionjetpack3.ui.detail.DetailActivity
import com.dicoding.submissionjetpack3.ui.detail.DetailViewModel.Companion.TV_SHOW
import com.dicoding.submissionjetpack3.ui.main.MainActivity
import com.dicoding.submissionjetpack3.utils.SortUtils.VOTE_BEST
import com.dicoding.submissionjetpack3.viewmodel.ViewModelFactory
import com.dicoding.submissionjetpack3.vo.Status

class TvFragment : Fragment(), TvAdapter.OnItemClickCallback {

    private lateinit var fragmentTvShowBinding: FragmentTvBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentTvShowBinding = FragmentTvBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            (activity as MainActivity)

            showProgressBar(true)
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvViewModel::class.java]

            val tvShowAdapter = TvAdapter()
            viewModel.getTvShows(VOTE_BEST).observe(viewLifecycleOwner, { tvShows ->
                if (tvShows != null) {
                    when (tvShows.status) {
                        Status.LOADING -> showProgressBar(true)
                        Status.SUCCESS -> {
                            showProgressBar(false)
                            tvShowAdapter.submitList(tvShows.data)
                            tvShowAdapter.setOnItemClickCallback(this)
                            tvShowAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            showProgressBar(false)
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(fragmentTvShowBinding.rvTv) {
                layoutManager = GridLayoutManager(context,2)
                setHasFixedSize(true)
                this.adapter = tvShowAdapter
            }
        }
    }

    override fun onItemClicked(id: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_FILM, id)
        intent.putExtra(DetailActivity.EXTRA_CATEGORY, TV_SHOW)

        context?.startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        activity?.menuInflater?.inflate(R.menu.sort_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_bookmark -> Toast.makeText(context, "!", Toast.LENGTH_SHORT).show()
            R.id.action_bookmark3 -> Toast.makeText(context, "!", Toast.LENGTH_SHORT).show()
            R.id.action_bookmark4 -> Toast.makeText(context, "!", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showProgressBar(state: Boolean) {
        fragmentTvShowBinding.pbTvShows.isVisible = state
        fragmentTvShowBinding.rvTv.isInvisible = state
    }

}