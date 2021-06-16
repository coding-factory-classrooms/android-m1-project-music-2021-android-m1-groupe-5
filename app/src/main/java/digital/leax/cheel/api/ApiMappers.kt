package digital.leax.cheel.api

import digital.leax.cheel.Artist


fun mapApiArtistWrapperToArtists(apiArtistsListWrapper: List<ApiArtists>): List<Artist> {
    val list = mutableListOf<Artist>()
    for (apiArtist in apiArtistsListWrapper) {
        list.add(mapApiArtist(apiArtist))
    }
    return list
}

fun mapApiArtist(apiMovie: ApiArtists): Artist {
    return Artist(
        id = apiMovie.id,
        name = apiMovie.name,
        genre_name = apiMovie.genre_name,
        album_cover_url = apiMovie.album_cover_url
    )
}