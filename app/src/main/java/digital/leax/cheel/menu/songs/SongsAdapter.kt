package digital.leax.cheel.menu.songs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import digital.leax.cheel.Artist
import digital.leax.cheel.Song
import digital.leax.cheel.SongArtist
import digital.leax.cheel.databinding.RowSongsBinding

class SongsAdapter(
    private var songs: List<SongArtist>,
    private val clickListener: View.OnClickListener,
    private val artist: Artist
) :
    RecyclerView.Adapter<SongsAdapter.ViewHolder>() {
    class ViewHolder(val binding: RowSongsBinding) : RecyclerView.ViewHolder(binding.root)
    var albumUrl:String? = null;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowSongsBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = songs[position]

        with(holder) {
            binding.songName.text = song.name
            binding.songDuration.text = song.duration.toString()

            if (artist.album_cover_url.isNotBlank()) {
                Picasso.get().load(artist.album_cover_url).into(binding.songCover)
            }

            binding.root.tag = song
            binding.root.setOnClickListener(clickListener)
        }
    }

    override fun getItemCount(): Int = songs.size

    fun updateDataSet(songs: List<SongArtist>) {
        this.songs = songs
        notifyDataSetChanged()
    }
}