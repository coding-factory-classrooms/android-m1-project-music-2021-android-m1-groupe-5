package org.robin.movie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

enum class Genre {
    SF,
    COMEDY,
}

data class Movie(
    val id: Int,
    val title: String,
    val genre: Genre,
    val releaseDate: String,
    val posterUrl: String
)

@Parcelize
data class ApiSession(
    val token: String,
    val username: String,
) : Parcelable
