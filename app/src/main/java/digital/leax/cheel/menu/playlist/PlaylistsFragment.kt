package digital.leax.cheel.menu.playlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import digital.leax.cheel.Artist
import digital.leax.cheel.databinding.FragmentPlaylistsBinding
import digital.leax.cheel.utils.getToken

private const val TAG = "PlaylistsFragment"

class PlaylistsFragment : Fragment() {

    private lateinit var binding: FragmentPlaylistsBinding
    private lateinit var adapter: PlaylistsAdapter
    private val model: PlaylistsViewModel by viewModels()
    private val args: PlaylistsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlaylistsBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PlaylistsAdapter(artists=  listOf(), clickListener =  { view ->
            val artists: Artist = view.tag as Artist
            Log.i(TAG, "Artist= $artists")
            navigateToSongsList(artists)
        }, context = requireContext())

        binding.playlistList.adapter = adapter
        binding.playlistList.layoutManager = LinearLayoutManager(context)

        model.getArtistsLiveData().observe(viewLifecycleOwner, { artist -> updateArtists(artist!!) })

        getToken(requireContext())?.let { model.loadArtists(it, requireContext(), args.playlist) }
    }

    private fun navigateToSongsList(artist : Artist) {
        val action =
            PlaylistsFragmentDirections.actionPlaylistsFragmentToSongsListFragment(
                artist = artist
            )
        findNavController().navigate(action)
    }

    private fun updateArtists(artists: List<Artist>) {
        adapter.updateDataSet(artists)
    }
}