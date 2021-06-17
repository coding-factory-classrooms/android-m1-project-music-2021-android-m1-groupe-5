package digital.leax.cheel.menu.player

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import digital.leax.cheel.Artist
import digital.leax.cheel.Song

private const val TAG = "PlayerViewModel"

sealed class PlayerViewModelState(
    open val infoText: String = "",
    open val showInfoText: Boolean = false,
    open val artist: Artist?,
    open val song: List<Song>?,
) {

    data class Success(override val artist: Artist, override val song: List<Song>) :
        PlayerViewModelState("", false, artist, song)

    data class Loading(override val infoText: String) :
        PlayerViewModelState(infoText, true, null, null)

    data class Failure(override val infoText: String) :
        PlayerViewModelState(infoText, true, null, null)
}


class PlayerViewModel : ViewModel() {

    private val state = MutableLiveData<PlayerViewModelState>()
    fun getState(): LiveData<PlayerViewModelState> = state

    fun loadView(song: List<Song>?, artist: Artist?) {
        state.value = PlayerViewModelState.Loading("Loading")

        if (artist == null || song == null) {
            Log.d(TAG, "onViewCreated: ARGS NULL")
            state.value = PlayerViewModelState.Failure("Aucune musique selectionné")
        } else {
            Log.d(TAG, "onViewCreated: ARGS $artist $song")
            state.value = PlayerViewModelState.Success(artist,song)
        }
    }

}