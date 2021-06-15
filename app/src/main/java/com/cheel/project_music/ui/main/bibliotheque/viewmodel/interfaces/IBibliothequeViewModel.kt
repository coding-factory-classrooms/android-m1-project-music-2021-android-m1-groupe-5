package com.cheel.project_music.ui.main.bibliotheque.viewmodel.interfaces

import androidx.lifecycle.LiveData
import com.cheel.project_music.ui.main.bibliotheque.viewmodel.BibliothequetState


interface IBibliothequeViewModel {
    fun getState() : LiveData<BibliothequetState>
    fun getArtists()
}