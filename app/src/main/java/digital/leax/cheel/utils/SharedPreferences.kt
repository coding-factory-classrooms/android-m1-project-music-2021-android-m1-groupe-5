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
fun setPlayList(c: Context,  playlistName: String,album: String) {
    val sharedPref =
        c.getSharedPreferences(c.getString(R.string.preference_file_key), Context.MODE_PRIVATE)

 val playlist = sharedPref.getStringSet(playlistName, setOf())?.toMutableList()

    if (playlist != null) {
        if (!playlist.contains(album)){
            playlist.add(album)
            with(sharedPref.edit()) {
                putStringSet(playlistName, playlist.toSet())
                apply()
            }

        }else  {
            playlist.remove(album)
            with(sharedPref.edit()) {
                putStringSet(playlistName, playlist.toSet())
                apply()
            }
        }
    }else  {
        val play = setOf(album)
        with(sharedPref.edit()) {
            putStringSet(playlistName, play)
            apply()
        }
    }
    Log.d(TAG, "set: $album in $playlistName")
}

fun getPlayList(c: Context, playlistName: String): Set<String>? {
    val sharedPref =
        c.getSharedPreferences(c.getString(R.string.preference_file_key), Context.MODE_PRIVATE)
    return sharedPref.getStringSet(playlistName, setOf())
}

fun getPlaylistList(c: Context): List<String> {
    val sharedPref =
        c.getSharedPreferences(c.getString(R.string.preference_file_key), Context.MODE_PRIVATE)

    val list = sharedPref.getStringSet(c.getString(R.string.preference_playlistList_key), setOf())

    if (list != null) {
        return list.toList()
    }
    return listOf()
}

fun addPlaylistList(c: Context, playlistName: String) {
    val sharedPref =
        c.getSharedPreferences(c.getString(R.string.preference_file_key), Context.MODE_PRIVATE)

    val list : MutableList<String>? = sharedPref.getStringSet(c.getString(R.string.preference_playlistList_key), setOf())?.toMutableList()
    if (list != null) {
        list.add(playlistName)
        with(sharedPref.edit()) {
            putStringSet(c.getString(R.string.preference_playlistList_key), list.toSet())
            apply()
        }
    }
}

fun setCurrentPlaylistSelected(c: Context, playlistName: String?){
    val sharedPref =
        c.getSharedPreferences(c.getString(R.string.preference_file_key), Context.MODE_PRIVATE)
    with(sharedPref.edit()) {
        putString(c.getString(R.string.preference_currentPlaylistSelected_key), playlistName)
        apply()
    }
    Log.d(TAG, "setCurrentPlaylistSelected: $playlistName")
}

fun getCurrentPlaylistSelected(c: Context): String? {
    val sharedPref =
        c.getSharedPreferences(c.getString(R.string.preference_file_key), Context.MODE_PRIVATE)
    return sharedPref.getString(c.getString(R.string.preference_currentPlaylistSelected_key), null)
}
