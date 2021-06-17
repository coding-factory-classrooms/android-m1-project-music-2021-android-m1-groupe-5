package digital.leax.cheel.menu.playlistlist

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import digital.leax.cheel.utils.getPlaylistList

private const val TAG = "PlaylistListViewModel"


class PlaylistListViewModel : ViewModel() {
    private val playlistLiveData = MutableLiveData<List<String>>()
    fun getplaylistLiveData(): LiveData<List<String>> = playlistLiveData

    fun loadPlaylistList(c: Context) {
        val playlistlist : List<String> = getPlaylistList(c)
        Log.d(TAG, "loadPlaylistList: $playlistlist")
        playlistLiveData.value = playlistlist
    }
}