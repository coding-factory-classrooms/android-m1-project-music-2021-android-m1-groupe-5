package digital.leax.cheel.menu.player

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import digital.leax.cheel.Artist
import digital.leax.cheel.Song
import digital.leax.cheel.databinding.FragmentPlayerBinding


private const val TAG = "PlayerFragment"

class PlayerFragment : Fragment() {

    private lateinit var binding: FragmentPlayerBinding
    private val args: PlayerFragmentArgs by navArgs()
    private val model: PlayerViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.getState().observe(viewLifecycleOwner, { updateUi(it!!) })

        model.loadView(artist = args.artist, song = args.song)
    }

    private fun updateUi(state: PlayerViewModelState) {
        return when (state) {
            is PlayerViewModelState.Failure -> {
                binding.informationText.isVisible = state.showInfoText
                binding.songUi.isVisible = !state.showInfoText
                binding.informationText.text = state.infoText
            }
            is PlayerViewModelState.Loading -> {
                binding.informationText.isVisible = state.showInfoText
                binding.songUi.isVisible = !state.showInfoText
                binding.informationText.text = state.infoText
            }
            is PlayerViewModelState.Success -> {
                binding.informationText.isVisible = state.showInfoText
                binding.songUi.isVisible = !state.showInfoText
                setSongUi(state.artist, state.song)
            }
        }
    }

    private fun setSongUi(artist: Artist, song: Song) {
        if (artist.album_cover_url.isNotBlank()) {
            Picasso.get().load(artist.album_cover_url).into(binding.playerSongImg)
        }
        binding.playerSongArtist.text = artist.name
        binding.playerSongTitle.text = song.name
        binding.playerPlayBtn.setOnClickListener {
            playAudio(song.file)
        }
        binding.playerStopBtn.setOnClickListener {
            stopAudio()
        }
        binding.playerContinueBtn.setOnClickListener {
            continueAudio()
        }
    }

    private var mediaPlayer: MediaPlayer? = null
    private var playbackPosition = 0

    private fun playAudio(url: String) {
        if(mediaPlayer != null && mediaPlayer!!.isPlaying()) {
            pauseAudio()
            return
        }

        Log.d(TAG, "playAudio: PLAY")
        killMediaPlayer()
        mediaPlayer = MediaPlayer()
        mediaPlayer!!.setDataSource(url)
        mediaPlayer!!.prepare()
        mediaPlayer!!.start()
    }

    private fun pauseAudio() {
            playbackPosition = mediaPlayer!!.getCurrentPosition()
            mediaPlayer!!.pause()
    }

    private fun stopAudio() {
        if(mediaPlayer != null) {
            mediaPlayer!!.stop()
            playbackPosition = 0
        }
    }

    private fun continueAudio() {
        if(mediaPlayer != null && !mediaPlayer!!.isPlaying()) {
            mediaPlayer!!.seekTo(playbackPosition)
            mediaPlayer!!.start()
        }
    }

        private fun killMediaPlayer() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer!!.release()
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }

}


