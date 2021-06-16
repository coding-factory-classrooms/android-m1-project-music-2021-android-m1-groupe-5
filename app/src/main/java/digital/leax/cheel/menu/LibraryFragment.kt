package digital.leax.cheel.menu

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import digital.leax.cheel.api.ApiArtists
import digital.leax.cheel.api.ArtistAPI
import digital.leax.cheel.databinding.FragmentLibraryBinding
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val TAG = "LibraryFragment"

class LibraryFragment : Fragment() {

    private lateinit var binding: FragmentLibraryBinding
    private val retrofitAPI: ArtistAPI;
    private val baseUrl = "https://music.gryt.tech/"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLibraryBinding.inflate(inflater, container, false)
        return binding.root
    }

    init {
        val headerAuthorizationInterceptor = Interceptor { chain ->
            var request = chain.request()
            val token : String? = context?.getSharedPreferences("ACCOUNT", Context.MODE_PRIVATE)
                ?.getString("authToken",null)
            if(token != null){
                val headers = request.headers.newBuilder().add("Authorization", "Token $token").build()
                request = request.newBuilder().headers(headers).build()
            }
            Log.v("test","tokensaved : $token")
            chain.proceed(request)

        }

        val clientBuilder = OkHttpClient.Builder().addInterceptor(headerAuthorizationInterceptor)

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(clientBuilder.build())
            .build()

        retrofitAPI = retrofit.create(ArtistAPI::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retrofitAPI.getArtists().enqueue(object : Callback<List<ApiArtists>> {
            override fun onResponse(
                call: Call<List<ApiArtists>>,
                response: Response<List<ApiArtists>>
            ) {
                if(response.code() == 200){
                    response.body()?.let {
                        Log.d("test","Artists:${it}" )
                    }
                }else{
                    Log.d("test", "Respose code : ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<ApiArtists>>, t: Throwable) {
                Log.d("test", t.message.toString())
            }

        })
    }

}