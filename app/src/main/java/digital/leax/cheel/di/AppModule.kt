package digital.leax.cheel.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import digital.leax.cheel.menu.songs.SongsDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) : SongsDatabase =
        Room.databaseBuilder(app, SongsDatabase::class.java, "songs_database")
            .build()
}