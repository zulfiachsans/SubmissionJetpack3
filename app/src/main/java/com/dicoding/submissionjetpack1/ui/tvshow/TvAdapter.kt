package com.dicoding.submissionjetpack1.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.submissionjetpack1.databinding.ItemRowBinding
import com.dicoding.submissionjetpack1.model.AllEntity
import com.dicoding.submissionjetpack1.ui.detail.DetailActivity
import com.dicoding.submissionjetpack1.ui.detail.DetailViewModel

class TvAdapter(tvFragment: TvFragment) : RecyclerView.Adapter<TvAdapter.TvViewHolder>() {
    private var tvShow = ArrayList<AllEntity>()

    fun setTv(tvShow: List<AllEntity>) {
        if (tvShow.isNullOrEmpty()) return
        this.tvShow.clear()
        this.tvShow.addAll(tvShow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val itemRowBinding =
            ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvViewHolder(itemRowBinding)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        holder.bind(tvShow[position])
    }

    override fun getItemCount(): Int = tvShow.size
    class TvViewHolder(private val binding: ItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: AllEntity) {
            with(binding) {
                tvTitle.text = tv.title

                Glide.with(itemView.context)
                    .load(tv.poster)
                    .into(imgItemPhoto)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_TYPE, tv.id)
                    intent.putExtra(DetailActivity.EXTRA_CATEGORY, DetailViewModel.TV_SHOWS)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}