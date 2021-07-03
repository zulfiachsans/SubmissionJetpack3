package com.dicoding.submissionjetpack3.data.source.remote.response.tv


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