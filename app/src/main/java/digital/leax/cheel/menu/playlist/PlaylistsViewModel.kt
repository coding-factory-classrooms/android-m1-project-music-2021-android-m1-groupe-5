package digital.leax.cheel.menu.playlist

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import digital.leax.cheel.Artist
import digital.leax.cheel.api.ApiArtists
import digital.leax.cheel.api.MusicDBApi
import digital.leax.cheel.api.mapApiArtistWrapperToArtists
import digital.leax.cheel.api.mapApiArtistWrapperToArtistsInPref
import digital.leax.cheel.utils.getPlayList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.Exception

private const val TAG = "PlaylistsViewModel"

class PlaylistsViewModel  : ViewModel(){
    private val artistsLiveData = MutableLiveData<List<Artist>>()
    fun getArtistsLiveData(): LiveData<List<Artist>> = artistsLiveData

    private val api: MusicDBApi

    init {
        // WARNING
        // This init should be done ONCE in the app
        // Application class is a good place
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://music.gryt.tech/")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        api = retrofit.create(MusicDBApi::class.java)
    }

    fun loadArtists(token : String, c : Context, playlistName : String) {
        val call = api.getArtists(token = "Token $token")

        call.enqueue(object : Callback<List<ApiArtists>> {
            override fun onResponse(
                call: Call<List<ApiArtists>>,
                response: Response<List<ApiArtists>>
            ) {

                val res = response.body()
                if (res != null){
                    Log.d(TAG, "onResponse: $res")
                    val artists = mapApiArtistWrapperToArtistsInPref(res, getPlayList(c, playlistName))
                    artistsLiveData.value = artists
                }
            }

            override fun onFailure(call: Call<List<ApiArtists>>, t: Throwable) {
                Log.d(TAG, "onFailure: FAIl")
            }

        })
    }
}