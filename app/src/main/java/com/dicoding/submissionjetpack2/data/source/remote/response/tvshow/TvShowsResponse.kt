package com.dicoding.submissionjetpack2.data.source.remote.response.tvshow

import com.google.gson.annotations.SerializedName

data class TvShowsResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<TvShows>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
