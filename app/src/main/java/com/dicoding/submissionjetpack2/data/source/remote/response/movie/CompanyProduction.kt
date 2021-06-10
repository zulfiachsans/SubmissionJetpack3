package com.dicoding.submissionjetpack2.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class CompanyProduction(
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo_path")
    val logoPath: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String
)
