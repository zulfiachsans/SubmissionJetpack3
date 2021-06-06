package com.dicoding.submissionjetpack1.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.dicoding.submissionjetpack1.R
import com.dicoding.submissionjetpack1.utils.DataDummy
import org.junit.Test
import org.junit.Before

class MainActivityTest {
    private val dummyMovies = DataDummy.generateDummyMovies()
    private val dummyTvShow = DataDummy.generateDummyTvShows()

    @Before
    fun setupAll() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun loadAll() {
        onView(withId(R.id.rv_movie))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
        onView(ViewMatchers.withText("TVSHOWS")).perform(ViewActions.click())
        onView(withId(R.id.rv_tv)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
    }
    @Test
    fun loadDetailMovies() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            ViewActions.click()
        ))
        onView(withId(R.id.collapsing)).check(ViewAssertions.matches(isDisplayed()))
     onView(withId(R.id.collapsing))
            .check(ViewAssertions.matches(ViewMatchers.withContentDescription(dummyMovies[0].title)))
        onView(withId(R.id.tv_release_overview))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_release_overview))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyMovies[0].realeaseYear)))
        onView(withId(R.id.tv_genre_overview))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_genre_overview))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyMovies[0].genre)))
        onView(withId(R.id.tv_duration_overview))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_duration_overview))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyMovies[0].duration)))
        onView(withId(R.id.tv_description_overview))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_description_overview))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyMovies[0].description)))
    }

    @Test
    fun loadDetailTvShows() {
        onView(ViewMatchers.withText("TVSHOWS")).perform(ViewActions.click())
        onView(withId(R.id.rv_tv)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            ViewActions.click()
        ))
        onView(withId(R.id.collapsing)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.collapsing))
            .check(ViewAssertions.matches(ViewMatchers.withContentDescription(dummyTvShow[0].title)))
        onView(withId(R.id.tv_release_overview))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_release_overview))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTvShow[0].realeaseYear)))
        onView(withId(R.id.tv_genre_overview))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_genre_overview))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTvShow[0].genre)))
        onView(withId(R.id.tv_duration_overview))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_duration_overview))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTvShow[0].duration)))
        onView(withId(R.id.tv_description_overview))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_description_overview))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyTvShow[0].description)))
    }

}