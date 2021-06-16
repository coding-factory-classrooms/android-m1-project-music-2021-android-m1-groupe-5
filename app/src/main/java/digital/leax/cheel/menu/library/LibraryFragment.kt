package digital.leax.cheel.menu.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import digital.leax.cheel.Artist
import digital.leax.cheel.databinding.FragmentLibraryBinding
import digital.leax.cheel.utils.getToken


private const val TAG = "LibraryFragment"

class LibraryFragment : Fragment() {

    private lateinit var binding: FragmentLibraryBinding
    private lateinit var adapter: LibraryAdapter
    private val model: LibraryViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLibraryBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = LibraryAdapter(listOf())

        binding.libraryList.adapter = adapter
        binding.libraryList.layoutManager = LinearLayoutManager(context)

        model.getMoviesLiveData().observe(viewLifecycleOwner, { artist -> updateArtists(artist!!) })

        getToken(requireContext())?.let { model.loadArtists(it) }
    }

    private fun updateArtists(artists: List<Artist>) {
        adapter.updateDataSet(artists)
    }

}