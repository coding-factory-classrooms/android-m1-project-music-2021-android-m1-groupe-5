package digital.leax.cheel.utils


fun getArtistName(s: String) : String{
    return s.split('-')[0].trim()
}

fun getAlbumName(s: String) : String{
    return s.split('-')[1].trim()
}