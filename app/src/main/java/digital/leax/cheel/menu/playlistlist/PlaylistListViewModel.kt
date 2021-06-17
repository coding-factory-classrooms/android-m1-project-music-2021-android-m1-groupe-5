package digital.leax.cheel.menu.playlistlist

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlaylistListViewModel : ViewModel() {
    private val playlistLiveData = MutableLiveData<List<String>>()
    fun getplaylistLiveData(): LiveData<List<String>> = playlistLiveData

    fun loadPlaylistList(c: Context) {

    }
}