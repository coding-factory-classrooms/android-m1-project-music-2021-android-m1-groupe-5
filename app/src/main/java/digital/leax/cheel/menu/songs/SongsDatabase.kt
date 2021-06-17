package digital.leax.cheel.menu.songs

import androidx.room.Database
import androidx.room.RoomDatabase
import digital.leax.cheel.Song
import digital.leax.cheel.api.ApiSong

@Database(entities = [ApiSong::class], version = 1)
abstract class SongsDatabase: RoomDatabase() {

    abstract fun songsDao(): SongsDao
}