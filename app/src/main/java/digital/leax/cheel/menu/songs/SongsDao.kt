package digital.leax.cheel.menu.songs;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import digital.leax.cheel.api.ApiSong
import kotlinx.coroutines.flow.Flow
import retrofit2.Call

@Dao
interface SongsDao {

    @Query("SELECT * FROM songs")
    fun getAllSongs(): Flow<List<ApiSong>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSongs(songs: Call<List<ApiSong>>)

    @Query("DELETE FROM songs")
    suspend fun deleteAllSongs()
}