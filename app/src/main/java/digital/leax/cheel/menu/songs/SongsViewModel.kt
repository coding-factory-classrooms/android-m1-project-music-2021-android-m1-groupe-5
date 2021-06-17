package digital.leax.cheel.menu.songs

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import digital.leax.cheel.Artist
import digital.leax.cheel.Song
import digital.leax.cheel.api.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

private const val TAG = "SongsViewModel"

class SongsViewModel @Inject constructor(
    repository: SongsRepository
) : ViewModel(){
    private val songsLiveData = MutableLiveData<List<Song>>()
    fun getSongsLiveData(): LiveData<List<Song>> = songsLiveData

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

    fun loadSongs(token : String, artistId : Int) {
        val call = api.getSongs(token = "Token $token", artistId)

        call.enqueue(object : Callback<List<ApiSong>> {
            override fun onResponse(call: Call<List<ApiSong>>, response: Response<List<ApiSong>>) {
                val res = response.body()
                if (res != null){
                    Log.d(TAG, "onResponse: $res")
                    val songs = mapApiSognsWrapperToSong(res)
                    songsLiveData.value = songs
                }
            }

            override fun onFailure(call: Call<List<ApiSong>>, t: Throwable) {
                Log.d(TAG, "onFailure: FAIL")

            }
        })
    }
}