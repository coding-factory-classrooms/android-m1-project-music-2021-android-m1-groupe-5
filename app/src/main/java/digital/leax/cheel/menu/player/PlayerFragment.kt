package digital.leax.cheel.menu.player

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import digital.leax.cheel.Artist
import digital.leax.cheel.R
import digital.leax.cheel.Song
import digital.leax.cheel.SongArtist
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

        model.loadView(songs = args.songs?.toList())
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
                setSongUi(state.songs)
            }
        }
    }

    private var index = 0

    private fun setSongUi(songs: List<SongArtist>) {

        setMediaPlayerSong(songs, index)

        binding.playerPlayPauseBtn.setOnClickListener {
            playPauseAudio()
        }
        binding.stopBtn.setOnClickListener {
            stopAudio()
        }

        binding.replayBtn.setOnClickListener {
            replayAudio()
        }

        binding.nextBtn.setOnClickListener {
            setMediaPlayerSong(songs, nextIndex(songs))
            playPauseAudio()
        }

        binding.prevBtn.setOnClickListener {
            setMediaPlayerSong(songs, prevIndex(songs))
            playPauseAudio()
        }
    }

    private fun nextIndex(song: List<SongArtist>): Int {
        index++
        if (index >= song.size) {
            index = 0
        }
        return index
    }

    private fun prevIndex(song: List<SongArtist>): Int {
        index--
        if (index < 0) {
            index = song.size-1
        }
        return index
    }

    private fun setMediaPlayerSong(song: List<SongArtist>, index: Int) {
        killMediaPlayer()
        mediaPlayer = MediaPlayer()
        mediaPlayer!!.setDataSource(song[index].file)
        mediaPlayer!!.prepare()

        if (song[index].album_cover_url.isNotBlank()) {
            Picasso.get().load(song[index].album_cover_url).into(binding.playerSongImg)
        }

        binding.playerSongArtist.text = song[index].nameArtist
        binding.playerSongTitle.text = song[index].name
    }

    private var mediaPlayer: MediaPlayer? = null

    private fun playPauseAudio() {
        if (mediaPlayer != null) {
            if (mediaPlayer!!.isPlaying) {
                pauseAudio()
                binding.playerPlayPauseBtn.setBackgroundResource(R.drawable.playbtn)
            } else {
                mediaPlayer!!.start()
                binding.playerPlayPauseBtn.setBackgroundResource(R.drawable.pausebtn)
            }
        }
    }

    private fun pauseAudio() {
        mediaPlayer!!.pause()
    }

    private fun stopAudio() {
        if (mediaPlayer != null) {
            pauseAudio()
            binding.playerPlayPauseBtn.setBackgroundResource(R.drawable.playbtn)
            mediaPlayer!!.seekTo(0)
        }
    }

    private fun replayAudio() {
        if (mediaPlayer != null) {
            mediaPlayer!!.seekTo(0)
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


