package digital.leax.cheel.menu.songs

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import digital.leax.cheel.Artist
import digital.leax.cheel.Song
import digital.leax.cheel.api.ApiArtists
import digital.leax.cheel.databinding.FragmentSongsListBinding
import digital.leax.cheel.menu.library.LibraryAdapter
import digital.leax.cheel.menu.library.LibraryViewModel
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



        adapter = SongsAdapter(listOf())

        binding.songsList.adapter = adapter
        binding.songsList.layoutManager = LinearLayoutManager(context)

        model.getSongsLiveData().observe(viewLifecycleOwner, { song -> updateSongs(song!!) })
        model.getArtistLiveData().observe(viewLifecycleOwner, { artist -> updateArtist(artist) })

        getToken(requireContext())?.let { model.loadSongs(it, artist.id) }

    }

    private fun updateArtist(artist: ApiArtists?) {
        artist?.let {
            adapter.albumUrl = artist.album_cover_url
            adapter.notifyDataSetChanged();
        }
    }

    private fun updateSongs(songs: List<Song>) {
        adapter.updateDataSet(songs)
    }

}