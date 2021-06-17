package digital.leax.cheel.api

import digital.leax.cheel.Artist
import digital.leax.cheel.Song
import digital.leax.cheel.SongArtist


fun mapApiArtistWrapperToArtists(apiArtistsListWrapper: List<ApiArtists>): List<Artist> {
    val list = mutableListOf<Artist>()
    for (apiArtist in apiArtistsListWrapper) {
        if (genreIsLofi(apiArtist.genre_name)) {
            list.add(mapApiArtist(apiArtist))
        }
    }
    return list
}

fun mapApiArtistWrapperToArtistsInPref(res: List<ApiArtists>, playList: Set<String>?): List<Artist> {
    val list = mutableListOf<Artist>()
    for (apiArtist in res) {
        if (playList != null) {
            if (playList.contains(apiArtist.name)) {
                list.add(mapApiArtist(apiArtist))
            }
        }
    }
    return list
}

fun mapApiSongsWrapperToSong(apiSongsListWrapper: List<ApiSong>, artists: Artist): List<SongArtist> {
    val list = mutableListOf<SongArtist>()
    for (apiSongs in apiSongsListWrapper) {
        list.add(mapApiSongArtist(apiSongs, artists ))
    }
    return list
}

fun mapApiSongsWrapperToSongList(data: List<List<ApiSong>>, artists: List<Artist>): List<SongArtist> {
    val listSongs = mutableListOf<SongArtist>()
    for (list in data) {
        for (song in list){
            listSongs.add(mapApiSongArtist(song, artists))
        }
    }
    return listSongs
}

fun mapApiSongArtist(song: ApiSong, artists: List<Artist>): SongArtist {
    val artistId = song.artist
    val artist = artists.find{it.id == artistId}
    return SongArtist(
        id = song.id,
        name = song.name,
        file = song.file,
        duration = song.duration,
        created_at = song.created_at,
        artist = song.artist,
        nameArtist= artist!!.name,
        genre_name= artist!!.genre_name,
        album_cover_url= artist!!.album_cover_url
    )

}

fun mapApiSongArtist(song: ApiSong, artist: Artist): SongArtist {
    return SongArtist(
        id = song.id,
        name = song.name,
        file = song.file,
        duration = song.duration,
        created_at = song.created_at,
        artist = song.artist,
        nameArtist= artist.name,
        genre_name= artist.genre_name,
        album_cover_url= artist.album_cover_url
    )

}


fun genreIsLofi(genreName: String): Boolean {
    return genreName.contains("lo-fi", true)
}


fun mapApiArtist(apiMovie: ApiArtists): Artist {
    return Artist(
        id = apiMovie.id,
        name = apiMovie.name,
        genre_name = apiMovie.genre_name,
        album_cover_url = apiMovie.album_cover_url
    )
}

fun mapApiSong(apiSong: ApiSong): Song {
    return Song(
        id = apiSong.id,
        name = apiSong.name,
        file = apiSong.file,
        duration = apiSong.duration,
        created_at = apiSong.created_at,
        artist = apiSong.artist,
    )
}