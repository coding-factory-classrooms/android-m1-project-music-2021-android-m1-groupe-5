package digital.leax.cheel.utils

import android.content.Context
import android.util.Log
import digital.leax.cheel.R

private const val TAG = "SharedPreferences"


fun setToken(c: Context, token: String) {
    val sharedPref =
        c.getSharedPreferences(c.getString(R.string.preference_file_key), Context.MODE_PRIVATE)
    with(sharedPref.edit()) {
        putString(c.getString(R.string.preference_token_key), token)
        apply()
    }
    Log.d(TAG, "setToken: $token")
}

fun getToken(c: Context): String? {
    val sharedPref =
        c.getSharedPreferences(c.getString(R.string.preference_file_key), Context.MODE_PRIVATE)
    return sharedPref.getString(c.getString(R.string.preference_token_key), null)
}
