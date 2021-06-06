package com.dicoding.submissionjetpack1.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.dicoding.submissionjetpack1.R
import com.dicoding.submissionjetpack1.ui.movies.MoviesFragment
import com.dicoding.submissionjetpack1.ui.tvshow.TvFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        carouselView.pageCount = carouselImages.size
        carouselView.setImageListener(imageListener)

        val fragmentList = listOf(MoviesFragment(), TvFragment())
        val tabTitle = listOf("MOVIES","TVSHOWS")
        viewpager.adapter = SectionPagerAdapter(fragmentList, this.supportFragmentManager, lifecycle)

        TabLayoutMediator(tabLayout2, viewpager){tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }
    val carouselImages = intArrayOf(
        R.drawable.latar_a_star,
        R.drawable.latar_alita,
        R.drawable.background_bohemian,
        R.drawable.latar_naruto_shipudden,
        R.drawable.latar_the_simpson,
        R.drawable.background_aquaman,
        R.drawable.background_invinity_war
    )

    val imageListener = ImageListener {position, imageView ->
        imageView.setImageResource(carouselImages[position])
    }

}