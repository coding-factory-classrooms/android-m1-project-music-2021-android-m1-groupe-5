package digital.leax.cheel.menu.songs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import digital.leax.cheel.Song
import digital.leax.cheel.databinding.FragmentSongsListBinding
import digital.leax.cheel.utils.getAlbumName
import digital.leax.cheel.utils.getArtistName
import digital.leax.cheel.utils.getToken


private const val TAG = "SongsListFragment"

class SongsListFragment : Fragment() {

    private lateinit var binding: FragmentSongsListBinding
    private val args: SongsListFragmentArgs by navArgs()
    private lateinit var adapter: SongsAdapter
    private val model: SongsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSongsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val artist = args.artist

        adapter = SongsAdapter(songs = listOf(), clickListener = { view ->
            val song: Song = view.tag as Song
            Log.i(TAG, "Song= $song")
            navigateToPlayer(song)
        }, artist = artist)

        binding.songsList.adapter = adapter
        binding.songsList.layoutManager = LinearLayoutManager(context)

        binding.albumTitle.text = getAlbumName(artist.name)
        binding.artistAlbumName.text = getArtistName(artist.name)

        binding.playAllBtn.setOnClickListener{
            navigateToPlayerAll(model.getAllSongs())
        }

        model.getSongsLiveData().observe(viewLifecycleOwner, { song -> updateSongs(song!!) })

        getToken(requireContext())?.let { model.loadSongs(it, artist.id) }

    }

    private fun navigateToPlayer(song: Song) {
        val action =
            SongsListFragmentDirections.actionSongsListFragmentToPlayerFragment(
                song = arrayOf(song),
                artist = args.artist
            )
        findNavController().navigate(action)
    }

    private fun navigateToPlayerAll(songs: List<Song>?) {
        if (songs != null){
            val action =
                SongsListFragmentDirections.actionSongsListFragmentToPlayerFragment(
                    song = songs.toTypedArray(),
                    artist = args.artist
                )
            findNavController().navigate(action)
        }else{
            Toast.makeText(context, "Aucun musique dans cet album", Toast.LENGTH_SHORT).show()
        }

    }

    private fun updateSongs(songs: List<Song>) {
        adapter.updateDataSet(songs)
    }

}