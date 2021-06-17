package digital.leax.cheel.menu.player

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import digital.leax.cheel.Artist
import digital.leax.cheel.Song
import digital.leax.cheel.SongArtist

private const val TAG = "PlayerViewModel"

sealed class PlayerViewModelState(
    open val infoText: String = "",
    open val showInfoText: Boolean = false,
    open val songs: List<SongArtist>?,
) {

    data class Success(override val songs: List<SongArtist>) :
        PlayerViewModelState("", false, songs)

    data class Loading(override val infoText: String) :
        PlayerViewModelState(infoText, true, null)

    data class Failure(override val infoText: String) :
        PlayerViewModelState(infoText, true, null)
}


class PlayerViewModel : ViewModel() {

    private val state = MutableLiveData<PlayerViewModelState>()
    fun getState(): LiveData<PlayerViewModelState> = state

    fun loadView(songs: List<SongArtist>?) {
        state.value = PlayerViewModelState.Loading("Loading")

        if (songs == null) {
            Log.d(TAG, "onViewCreated: ARGS NULL")
            state.value = PlayerViewModelState.Failure("Aucune musique selectionné")
        } else {
            Log.d(TAG, "onViewCreated: ARGS $songs")
            state.value = PlayerViewModelState.Success(songs)
        }
    }

}