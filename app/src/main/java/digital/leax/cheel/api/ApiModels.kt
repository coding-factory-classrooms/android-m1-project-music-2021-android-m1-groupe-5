package digital.leax.cheel.api

import android.net.Uri
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiAuthTokenWrapper(
    val token: String,
)

@JsonClass(generateAdapter = true)
data class ApiCredentials(
    val username: String,
    val password: String
)

@JsonClass(generateAdapter = true)
data class ApiArtists(
    val name: String,
    val genre_name: String,
    val album_cover_url: String
)





