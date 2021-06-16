package digital.leax.cheel.menu.library


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import digital.leax.cheel.Artist
import digital.leax.cheel.databinding.RowArtistBinding
import digital.leax.cheel.utils.setPlayList


class LibraryAdapter(
    private var artists: List<Artist>,
    private val clickListener: View.OnClickListener,
    private val context: Context
) :
    RecyclerView.Adapter<LibraryAdapter.ViewHolder>() {
    class ViewHolder(val binding: RowArtistBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowArtistBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val artist = artists[position]
        with(holder) {
            binding.artistName.text = artist.name
            binding.artistGenre.text = artist.genre_name

            if (artist.album_cover_url.isNotBlank()) {
                Picasso.get().load(artist.album_cover_url).into(binding.artistCover)
            }

            binding.root.tag = artist
            binding.root.setOnClickListener(clickListener)

            binding.addPlaylistBtn.setOnClickListener{
                setPlayList(context, artist.name)
                Toast.makeText(context, "Ajout dans la playlist de ${artist.name}", Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun getItemCount(): Int = artists.size

    fun updateDataSet(artists: List<Artist>) {
        this.artists = artists
        notifyDataSetChanged()
    }
}