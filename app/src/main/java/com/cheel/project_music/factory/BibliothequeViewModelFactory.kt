package com.cheel.project_music.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cheel.project_music.api.service.APIArtist
import com.cheel.project_music.ui.main.bibliotheque.viewmodel.BibliothequeViewModel

@Suppress("UNCHECKED_CAST")
class BibliothequeViewModelFactory(private val apiArtist: APIArtist, private val application: Application) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BibliothequeViewModel(apiArtist,application) as T
    }
}