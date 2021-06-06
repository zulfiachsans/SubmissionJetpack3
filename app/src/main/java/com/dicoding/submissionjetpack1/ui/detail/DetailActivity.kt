package com.dicoding.submissionjetpack1.ui.detail

import android.content.Context
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.dicoding.submissionjetpack1.R
import com.dicoding.submissionjetpack1.databinding.ActivityDetailBinding
import com.dicoding.submissionjetpack1.model.AllEntity
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_detail.*
import kotlin.math.abs

class DetailActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {

    companion object {
        const val EXTRA_TYPE = "extra_type"
        const val EXTRA_CATEGORY = "extra_category"
    }

    private lateinit var detailBinding: ActivityDetailBinding
    private val percentageToShowImage = 20
    private var maxScrollSize = 0
    private var imageHidden = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        supportActionBar?.hide()

        detailBinding.toolbar.setNavigationOnClickListener { onBackPressed() }
        detailBinding.appbar.addOnOffsetChangedListener(this)

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]
        val extras = intent.extras
        if (extras != null) {
            val dataId = extras.getString(EXTRA_TYPE)
            val dataCategory = extras.getString(EXTRA_CATEGORY)

            if (dataId != null && dataCategory != null) {
                viewModel.setAll(dataId, dataCategory)
                val movie = viewModel.getAllDetail()
                dataDetail(movie)
            }
        }

    }

    private fun dataDetail(data: AllEntity) {
        detailBinding.collapsing.title = data.title
        detailBinding.tvReleaseOverview.text = data.realeaseYear
        detailBinding.tvGenreOverview.text = data.genre
        detailBinding.tvDurationOverview.text = data.duration
        detailBinding.tvDescriptionOverview.text = data.description
        setGlideImage(this@DetailActivity, data.poster, profile_movie)
        setGlideImage(this@DetailActivity, data.background, iv_detail)
        setColorByPalette(data.poster)
    }

    private fun setColorByPalette(poster: Int) {
        val colorBitmap = BitmapFactory.decodeResource(resources, poster)

        Palette.from(colorBitmap).generate { palette ->
            val defValue = resources.getColor(R.color.dark, theme)
            detailBinding.cardDetail.setCardBackgroundColor(
                palette?.getDarkMutedColor(defValue) ?: defValue
            )
            detailBinding.collapsing.setContentScrimColor(
                palette?.getDarkMutedColor(defValue) ?: defValue
            )
            window.statusBarColor = palette?.getDarkMutedColor(defValue) ?: defValue
        }
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        if (maxScrollSize == 0) maxScrollSize = appBarLayout!!.totalScrollRange

        val currentScrollPercentage: Int = (abs(verticalOffset) * 100 / maxScrollSize)

        if (currentScrollPercentage >= percentageToShowImage) {
            if (!imageHidden) {
                imageHidden = true
            }
        }

        if (currentScrollPercentage < percentageToShowImage) {
            if (imageHidden) {
                imageHidden = false
            }
        }
    }
    fun setGlideImage(context: Context, imagePath: Int, imageView: ImageView) {
        Glide.with(context).clear(imageView)
        Glide.with(context).load(imagePath).into(imageView)
    }
}