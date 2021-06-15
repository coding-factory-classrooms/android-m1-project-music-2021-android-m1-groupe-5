package com.cheel.project_music.api.service

import com.cheel.project_music.api.model.Auth
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APIAccount {
    @POST("/api-token-auth/")
    fun getToken(@Body authRequest: Auth.Request) : Call<Auth.Response>
}