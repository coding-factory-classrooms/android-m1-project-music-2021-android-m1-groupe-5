package digital.leax.cheel.api

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
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
    val id : Int,
    val name: String,
    val genre_name: String,
    val album_cover_url: String
)

@JsonClass(generateAdapter = true)
@Entity(tableName = "songs")
data class ApiSong(
    @PrimaryKey val id : Int,
    val name: String,
    val file: String,
    val duration: Int,
    val created_at: String,
    val artist: Int
)






