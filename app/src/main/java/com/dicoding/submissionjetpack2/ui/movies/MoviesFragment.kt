package com.dicoding.submissionjetpack2.ui.movies

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.submissionjetpack2.databinding.FragmentMoviesBinding
import com.dicoding.submissionjetpack2.ui.detail.DetailActivity
import com.dicoding.submissionjetpack2.ui.detail.DetailViewModel.Companion.MOVIES
import com.dicoding.submissionjetpack2.viewmodel.ViewModelFactory

class MoviesFragment : Fragment(), MoviesAdapter.OnItemClickCallback {

    private lateinit var fragmentMovieBinding: FragmentMoviesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentMovieBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            showProgressBar(true)

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MoviesViewModel::class.java]
            val movieAdapter = MoviesAdapter()

            viewModel.getMovies().observe(viewLifecycleOwner, { movies ->
                showProgressBar(false)
                movieAdapter.setMovies(movies)
                movieAdapter.setOnItemClickCallback(this)
                movieAdapter.notifyDataSetChanged()
            })

            val marginVertical = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f, resources.displayMetrics)

            with(fragmentMovieBinding.rvMovie) {
                layoutManager = GridLayoutManager(context,2)
                setHasFixedSize(true)
                this.adapter = movieAdapter
            }
        }
    }

    override fun onItemClicked(id: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_TYPE, id)
        intent.putExtra(DetailActivity.EXTRA_CATEGORY, MOVIES)

        context?.startActivity(intent)
    }

    private fun showProgressBar(state: Boolean) {
        fragmentMovieBinding.progressMovies.isVisible = state
        fragmentMovieBinding.rvMovie.isInvisible = state
    }
}