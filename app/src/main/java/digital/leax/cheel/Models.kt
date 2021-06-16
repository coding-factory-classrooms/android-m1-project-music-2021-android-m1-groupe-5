package digital.leax.cheel

data class AuthToken(
    val token: String,
)

data class Artist(
    val id: Int,
    val name: String,
    val genre_name: String,
    val album_cover_url: String
)
