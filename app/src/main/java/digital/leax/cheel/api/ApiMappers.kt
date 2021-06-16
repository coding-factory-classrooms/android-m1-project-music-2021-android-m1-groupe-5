package digital.leax.cheel.api

import digital.leax.cheel.Artist
import digital.leax.cheel.Song


fun mapApiArtistWrapperToArtists(apiArtistsListWrapper: List<ApiArtists>): List<Artist> {
    val list = mutableListOf<Artist>()
    for (apiArtist in apiArtistsListWrapper) {
        if (genreIsLofi(apiArtist.genre_name)) {
            list.add(mapApiArtist(apiArtist))
        }
    }
    return list
}

fun mapApiSognsWrapperToSong(apiSongsListWrapper: List<ApiSong>): List<Song> {
    val list = mutableListOf<Song>()
    for (apiSongs in apiSongsListWrapper) {
        list.add(mapApiSong(apiSongs))
    }
    return list
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