package digital.leax.cheel.api

import retrofit2.Call
import retrofit2.http.*


interface MusicDBApi {

    @POST("api-token-auth/")
    fun postAuth(
        @Body cred: ApiCredentials
    ): Call<ApiAuthTokenWrapper>

    @GET("api/artists/")
    fun getArtistsCoroutine(@Header("Authorization") token : String): List<ApiArtists>

    @GET("api/artists/")
    fun getArtists( @Header("Authorization") token : String): Call<List<ApiArtists>>

    @GET("api/artists/{artistID}")
    fun getArtistsById(@Path(value = "artistID") artistID: Long): Call<ApiArtists>

}