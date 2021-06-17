package digital.leax.cheel.menu.playlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import digital.leax.cheel.Artist
import digital.leax.cheel.databinding.RowPlaylistBinding

class PlaylistsAdapter(
    private var artists: List<Artist>,
    private val clickListener: View.OnClickListener,
    private val context: Context
) :
    RecyclerView.Adapter<PlaylistsAdapter.ViewHolder>() {
    class ViewHolder(val binding: RowPlaylistBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowPlaylistBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val artist = artists[position]
        with(holder) {
            binding.artistNamePlayList.text = artist.name
            binding.artistGenrePlayList.text = artist.genre_name

            if (artist.album_cover_url.isNotBlank()) {
                Picasso.get().load(artist.album_cover_url).into(binding.artistCoverPlayList)
            }

            binding.root.tag = artist
            binding.root.setOnClickListener(clickListener)
        }
    }

    override fun getItemCount(): Int = artists.size

    fun updateDataSet(artists: List<Artist>) {
        this.artists = artists
        notifyDataSetChanged()
    }
}