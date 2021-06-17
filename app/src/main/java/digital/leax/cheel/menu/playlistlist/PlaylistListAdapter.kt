package digital.leax.cheel.menu.playlistlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import digital.leax.cheel.databinding.RowPlaylistListBinding
import digital.leax.cheel.utils.setCurrentPlaylistSelected

class PlaylistListAdapter (
    private var playlistList: List<String>,
    private val clickListener: View.OnClickListener,
    private val context: Context
) :
    RecyclerView.Adapter<PlaylistListAdapter.ViewHolder>() {
    class ViewHolder(val binding: RowPlaylistListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowPlaylistListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val playlist = playlistList[position]
        with(holder) {
            binding.playlistListName.text = playlist

            binding.root.tag = playlist
            binding.root.setOnClickListener(clickListener)

            binding.currentPlaylistBtn.setOnClickListener {
                setCurrentPlaylistSelected(context, playlist)
            }
        }
    }

    override fun getItemCount(): Int = playlistList.size

    fun updateDataSet(list: List<String>) {
        this.playlistList = list
        notifyDataSetChanged()
    }
}