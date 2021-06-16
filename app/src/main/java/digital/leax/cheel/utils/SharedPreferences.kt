package digital.leax.cheel.utils

import android.annotation.SuppressLint
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

@SuppressLint("MutatingSharedPrefs")
fun setPlayList(c: Context, album: String) {
    val sharedPref =
        c.getSharedPreferences(c.getString(R.string.preference_file_key), Context.MODE_PRIVATE)

 val playlist = sharedPref.getStringSet(c.getString(R.string.preference_playlist), setOf())

    if (playlist != null) {
        if (!playlist.contains(album)){
            playlist.add(album)
            with(sharedPref.edit()) {
                putStringSet(c.getString(R.string.preference_playlist), playlist)
                apply()
            }

        }else  {
            playlist.remove(album)
            with(sharedPref.edit()) {
                putStringSet(c.getString(R.string.preference_playlist), playlist)
                apply()
            }
        }
    }else  {
        val play = setOf(album)
        with(sharedPref.edit()) {
            putStringSet(c.getString(R.string.preference_playlist), play)
            apply()
        }
    }
    Log.d(TAG, "set: $album")
}

fun getPlayList(c: Context) : Set<String>?{
    val sharedPref =
        c.getSharedPreferences(c.getString(R.string.preference_file_key), Context.MODE_PRIVATE)
    return sharedPref.getStringSet(c.getString(R.string.preference_playlist), setOf())
}
