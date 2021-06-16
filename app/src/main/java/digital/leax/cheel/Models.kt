package digital.leax.cheel

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

data class AuthToken(
    val token: String,
)

@Parcelize
data class Artist(
    val id: Int,
    val name: String,
    val genre_name: String,
    val album_cover_url: String
): Parcelable

data class Song(
    val id : Int,
    val name: String,
    val file: String,
    val duration: Int,
    val created_at: String,
    val artist: Int
)