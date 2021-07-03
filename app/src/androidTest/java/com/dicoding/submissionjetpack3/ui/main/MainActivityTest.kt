package com.dicoding.submissionjetpack3.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.core.IsNot.not
import androidx.test.espresso.matcher.ViewMatchers.*
import com.dicoding.submissionjetpack3.R
import com.dicoding.submissionjetpack3.utils.DataDummy
import com.dicoding.submissionjetpack3.utils.EspressoIdlingResource
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest {
    private val dummyMovies = DataDummy.getMovies()
    private val dummyTvShow = DataDummy.getTvShows()

    @Before
    fun setupAll() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies.size
            )
        )
    }
    @Test
    fun loadTvShows(){
        onView(withId(R.id.tvShowFragment)).perform(click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShow.size
            )
        )
    }

    @Test
    fun loadDetailMovies() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.iv_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.coordinator_layout)).perform(ViewActions.swipeUp())
        onView(withId(R.id.profile_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.collapsing)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_duration_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description_overview)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailTvShows() {
        onView(withId(R.id.tvShowFragment)).perform(click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.iv_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.coordinator_layout)).perform(ViewActions.swipeUp())
        onView(withId(R.id.profile_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.collapsing)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_duration_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description_overview)).check(matches(isDisplayed()))
    }

    @Test
    fun loadFavoriteMovies() {
        onView(withId(R.id.favoriteFragment)).perform(click())
        onView(withId(R.id.rv_fav_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_fav_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies.size
            )
        )
    }

    @Test
    fun loadDetailFavMovies() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.fab_add_to_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())

        onView(withId(R.id.favoriteFragment)).perform(click())
        onView(withId(R.id.rv_fav_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.iv_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.coordinator_layout)).perform(ViewActions.swipeUp())
        onView(withId(R.id.profile_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.collapsing)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_duration_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description_overview)).check(matches(isDisplayed()))
    }
    @Test
    fun loadFavTvShows() {
        onView(withId(R.id.favoriteFragment)).perform(click())
        onView(withText("TVSHOWS")).perform(click())
        onView(withId(R.id.rv_fav_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_fav_tv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShow.size
            )
        )
    }
    @Test
    fun loadDetailFavTvShow() {
        onView(withId(R.id.tvShowFragment)).perform(click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.fab_add_to_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())

        onView(withId(R.id.favoriteFragment)).perform(click())
        onView(withText("TVSHOWS")).perform(click())
        onView(withId(R.id.rv_fav_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.iv_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.coordinator_layout)).perform(ViewActions.swipeUp())
        onView(withId(R.id.profile_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.collapsing)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_duration_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description_overview)).check(matches(isDisplayed()))
    }
}