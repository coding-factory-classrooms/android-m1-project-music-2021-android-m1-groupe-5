package digital.leax.cheel.menu.songs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import digital.leax.cheel.Artist
import digital.leax.cheel.Song
import digital.leax.cheel.databinding.FragmentSongsListBinding
import digital.leax.cheel.databinding.RowArtistBinding
import digital.leax.cheel.databinding.RowSongsBinding

class SongsAdapter(
    private var songs: List<Song>
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
        albumUrl?.let { Picasso.get().load(albumUrl).into(holder.binding.songCover) }


        with(holder) {
            binding.songName.text = song.name
            binding.songDuration.text = song.duration.toString()



        }
    }

    override fun getItemCount(): Int = songs.size

    fun updateDataSet(songs: List<Song>) {
        this.songs = songs
        notifyDataSetChanged()
    }
}