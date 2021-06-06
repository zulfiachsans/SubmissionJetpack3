package com.dicoding.submissionjetpack1.ui.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.submissionjetpack1.R
import com.dicoding.submissionjetpack1.model.AllEntity
import kotlinx.android.synthetic.main.fragment_tv.*

class TvFragment : Fragment() {
    private lateinit var tvViewModel: TvViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let {
            tvViewModel = ViewModelProvider(
                it,
                ViewModelProvider.NewInstanceFactory()
            )[TvViewModel::class.java]
        }

        val listTv = tvViewModel.getTv()
        setRecycler(listTv)
    }

    private fun setRecycler(data: List<AllEntity>) {
        rv_tv.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = TvAdapter(this@TvFragment)
        }.also {
            it.adapter.let { adapter ->
                when (adapter) {
                    is TvAdapter -> {
                        adapter.setTv(data)
                    }
                }
            }
        }
    }
}