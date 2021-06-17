package digital.leax.cheel.menu.playlistlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import digital.leax.cheel.Artist
import digital.leax.cheel.databinding.FragmentPlaylistListBinding
import digital.leax.cheel.menu.playlist.PlaylistsAdapter
import digital.leax.cheel.menu.playlist.PlaylistsFragmentDirections
import digital.leax.cheel.utils.getToken

private const val TAG = "PlaylistListFragment"

class PlaylistListFragment : Fragment() {

    private lateinit var binding: FragmentPlaylistListBinding
    private lateinit var adapter: PlaylistListAdapter
    private val model: PlaylistListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlaylistListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PlaylistListAdapter(playlistList=  listOf(), clickListener =  { view ->
            val playListName: String = view.tag as String
            Log.i(TAG, "PlayList Name= $playListName")
            navigateToPlaylist(playListName)
        }, context = requireContext())

        binding.playlistView.adapter = adapter
        binding.playlistView.layoutManager = LinearLayoutManager(context)

        model.getplaylistLiveData().observe(viewLifecycleOwner, { playlistList -> updatePlaylistList(playlistList!!) })
        model.loadPlaylistList(requireContext())
    }

    private fun updatePlaylistList(playlistList: List<String>) {
        adapter.updateDataSet(playlistList)
    }

    private fun navigateToPlaylist(playListName: String) {
        val action =
            PlaylistListFragmentDirections.actionPlaylistListFragmentToPlaylistsFragment(
                playlist = playListName
            )
        findNavController().navigate(action)
    }



}