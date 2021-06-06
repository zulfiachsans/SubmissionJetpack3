package com.dicoding.submissionjetpack1.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.Duration

@Parcelize
data class AllEntity(
    var id: String,
    var title: String,
    var description: String,
    var genre: String,
    var realeaseYear: String,
    var duration: String,
    var poster: Int,
    var background: Int
) : Parcelable