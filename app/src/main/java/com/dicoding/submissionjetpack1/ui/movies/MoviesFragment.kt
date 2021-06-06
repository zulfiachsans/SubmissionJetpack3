package com.dicoding.submissionjetpack1.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.submissionjetpack1.R
import com.dicoding.submissionjetpack1.model.AllEntity
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : Fragment() {
    private lateinit var viewModel: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let {
            viewModel = ViewModelProvider(
                it,
                ViewModelProvider.NewInstanceFactory()
            )[MoviesViewModel::class.java]
        }

        val listMovie = viewModel.getMovies()
        setRecycler(listMovie)
    }

    private fun setRecycler(data: List<AllEntity>) {
        rv_movie.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = MoviesAdapter(this@MoviesFragment)
        }.also {
            it.adapter.let { adapter ->
                when (adapter) {
                    is MoviesAdapter -> {
                        adapter.setMovies(data)
                    }
                }
            }
        }
    }
}