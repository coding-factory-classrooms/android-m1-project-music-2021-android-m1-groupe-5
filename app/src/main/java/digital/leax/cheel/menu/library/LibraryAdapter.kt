package digital.leax.cheel.menu.library


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import digital.leax.cheel.Artist
import digital.leax.cheel.databinding.RowArtistBinding


class LibraryAdapter(
    private var artits: List<Artist>,
) :
    RecyclerView.Adapter<LibraryAdapter.ViewHolder>() {
    class ViewHolder(val binding: RowArtistBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowArtistBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val artist = artits[position]
        with(holder) {
            binding.name.text = artist.name
            binding.genre.text = artist.genre_name

            if (artist.album_cover_url.isNotBlank()) {
                Picasso.get().load(artist.album_cover_url).into(binding.cover)
            }

        }
    }

    override fun getItemCount(): Int = artits.size

    fun updateDataSet(artists: List<Artist>) {
        this.artits = artists
        notifyDataSetChanged()
    }
}