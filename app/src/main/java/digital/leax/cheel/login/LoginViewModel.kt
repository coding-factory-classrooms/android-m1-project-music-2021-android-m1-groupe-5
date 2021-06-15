package digital.leax.cheel.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import digital.leax.cheel.api.ApiAuthTokenWrapper
import digital.leax.cheel.api.ApiCredentials
import digital.leax.cheel.api.MusicDBApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.math.log

private const val TAG = "LoginViewModel"


sealed class LoginViewModelState(
    open val errorMessage: String = "",
    open val loginButtonEnable: Boolean = false
) {
    object Loading : LoginViewModelState()
    object Success : LoginViewModelState()
    data class UpdateLogin(override val loginButtonEnable: Boolean) :
        LoginViewModelState(loginButtonEnable = loginButtonEnable)
    data class Failure(override val errorMessage: String) :
        LoginViewModelState(errorMessage = errorMessage, loginButtonEnable = true)
}

class LoginViewModel : ViewModel() {


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

    private val state = MutableLiveData<LoginViewModelState>()
    fun getState(): LiveData<LoginViewModelState> = state

    fun login(login: String, password: String) {
        state.value = LoginViewModelState.Loading


//        val call = api.postAuth(ApiCredentials(username = "groupe5", password = "C1gbOcE0w5"))
        val call = api.postAuth(ApiCredentials(username = login, password = password))
        call.enqueue(object : Callback<ApiAuthTokenWrapper> {
            override fun onResponse(
                call: Call<ApiAuthTokenWrapper>,
                response: Response<ApiAuthTokenWrapper>
            ) {
                val res = response.body()
                if (res == null){
                    state.value = LoginViewModelState.Failure("Invalid credential")
                }else {
                    state.value = LoginViewModelState.Success
                }
            }

            override fun onFailure(call: Call<ApiAuthTokenWrapper>, t: Throwable) {
                Log.d(TAG, "onFailure: FAIL")
                state.value = LoginViewModelState.Failure("Error on datebase resquest")

            }

        })
    }

    fun UpdateLogin(login: String, password: String) {
        val buttonEnabled = login.isNotBlank() && password.isNotBlank()
        state.value = LoginViewModelState.UpdateLogin(buttonEnabled)
    }

}


