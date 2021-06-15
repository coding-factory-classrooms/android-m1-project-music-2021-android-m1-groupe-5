package digital.leax.cheel.api

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
