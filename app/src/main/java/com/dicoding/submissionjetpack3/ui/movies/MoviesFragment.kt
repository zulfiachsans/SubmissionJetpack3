package com.dicoding.submissionjetpack3.ui.movies

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.submissionjetpack3.R
import com.dicoding.submissionjetpack3.data.source.local.entity.MovieEntity
import com.dicoding.submissionjetpack3.databinding.FragmentMovieBinding
import com.dicoding.submissionjetpack3.ui.detail.DetailActivity
import com.dicoding.submissionjetpack3.ui.detail.DetailViewModel.Companion.MOVIE
import com.dicoding.submissionjetpack3.ui.main.MainActivity
import com.dicoding.submissionjetpack3.utils.SortUtils.RANDOM
import com.dicoding.submissionjetpack3.utils.SortUtils.VOTE_BEST
import com.dicoding.submissionjetpack3.utils.SortUtils.VOTE_WORST
import com.dicoding.submissionjetpack3.viewmodel.ViewModelFactory
import com.dicoding.submissionjetpack3.vo.Resource
import com.dicoding.submissionjetpack3.vo.Status

class MoviesFragment : Fragment(), MoviesAdapter.OnItemClickCallback {

    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    private lateinit var viewModel: MoviesViewModel
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            (activity as MainActivity)

            showProgressBar(true)
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[MoviesViewModel::class.java]

            moviesAdapter = MoviesAdapter()
            viewModel.getMovies(VOTE_BEST).observe(viewLifecycleOwner, movieObserver)


            with(fragmentMovieBinding.rvMovies) {
                layoutManager = GridLayoutManager(context,2)
                setHasFixedSize(true)
                this.adapter = moviesAdapter
            }
        }
    }

    private val movieObserver = Observer<Resource<PagedList<MovieEntity>>> { movies ->
        if (movies != null) {
            when (movies.status) {
                Status.LOADING -> showProgressBar(true)
                Status.SUCCESS -> {
                    showProgressBar(false)
                    moviesAdapter.submitList(movies.data)
                    moviesAdapter.setOnItemClickCallback(this)
                    moviesAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    showProgressBar(false)
                    Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onItemClicked(id: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_FILM, id)
        intent.putExtra(DetailActivity.EXTRA_CATEGORY, MOVIE)

        context?.startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        activity?.menuInflater?.inflate(R.menu.sort_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var sort = ""
        when (item.itemId) {
            R.id.action_bookmark -> sort = VOTE_BEST
            R.id.action_bookmark3 -> sort = VOTE_WORST
            R.id.action_bookmark4 -> sort = RANDOM
        }

        viewModel.getMovies(sort).observe(viewLifecycleOwner, movieObserver)
        item.isChecked = true

        return super.onOptionsItemSelected(item)
    }

    private fun showProgressBar(state: Boolean) {
        fragmentMovieBinding.pbMovies.isVisible = state
        fragmentMovieBinding.rvMovies.isInvisible = state
    }

}