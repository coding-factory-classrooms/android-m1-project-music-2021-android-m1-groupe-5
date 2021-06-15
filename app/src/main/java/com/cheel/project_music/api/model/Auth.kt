package com.cheel.project_music.api.model

import com.squareup.moshi.JsonClass


sealed class Auth{
    @JsonClass(generateAdapter = true)
    data class Request(val username: String = "groupe5",val password:String = "C1gbOcE0w5")

    @JsonClass(generateAdapter = true)
    data class Response(val token: String)
}
