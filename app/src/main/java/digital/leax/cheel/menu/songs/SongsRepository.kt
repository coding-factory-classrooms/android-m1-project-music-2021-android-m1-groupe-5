package digital.leax.cheel.menu.songs

import androidx.room.withTransaction
import digital.leax.cheel.api.MusicDBApi
import digital.leax.cheel.utils.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class SongsRepository @Inject constructor(
    private val api: MusicDBApi,
    private val db: SongsDatabase
) {
    private val songsDao = db.songsDao()

    fun getSongs(token : String, artistId : Int) = networkBoundResource(
        query = {
            songsDao.getAllSongs()
        },
        fetch = {
            delay(2000)
            api.getSongs(token = "Token $token", artistId)
        },
        saveFetchResult = { songs ->
            db.withTransaction {
                songsDao.deleteAllSongs()
                songsDao.insertSongs(songs)
            }
        }
    )
}