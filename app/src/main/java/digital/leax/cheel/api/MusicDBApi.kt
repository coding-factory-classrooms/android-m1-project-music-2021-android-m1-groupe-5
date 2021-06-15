package digital.leax.cheel.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface MusicDBApi {

    @POST("api-token-auth/")
    fun postAuth(
        @Body cred: ApiCredentials
    ): Call<ApiAuthTokenWrapper>
}