package digital.leax.cheel.api

import retrofit2.Call
import retrofit2.http.*

interface ArtistAPI {
    @GET("api/artists/")
    fun getArtists(): Call<List<ApiArtists>>

    @GET("api/artists/{artistID}")
    fun getArtistsById(@Path(value = "artistID") artistID: Long): Call<ApiArtists>
}