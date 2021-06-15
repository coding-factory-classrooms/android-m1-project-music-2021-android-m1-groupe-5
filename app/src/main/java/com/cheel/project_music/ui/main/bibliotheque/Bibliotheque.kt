package com.cheel.project_music.ui.main.bibliotheque

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.cheel.project_music.ui.main.bibliotheque.viewmodel.BibliothequeViewModel
import com.cheel.project_music.R
import com.cheel.project_music.api.RetrofitFactory
import com.cheel.project_music.api.service.APIArtist
import com.cheel.project_music.common.makeToast
import com.cheel.project_music.factory.BibliothequeViewModelFactory
import com.cheel.project_music.ui.main.bibliotheque.viewmodel.BibliothequetState

class Bibliotheque : Fragment() {

    companion object {
        fun newInstance() = Bibliotheque()
    }

    private lateinit var viewModel: BibliothequeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bibliotheque_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(viewModelStore, BibliothequeViewModelFactory(
            RetrofitFactory(requireContext())
            .createService(APIArtist::class.java), activity?.application!!
        )
        ).get(BibliothequeViewModel::class.java)
        viewModel.getArtists();

        viewModel.getState().observe(viewLifecycleOwner, Observer { updateUI(it) })

    }

    private fun updateUI(state: BibliothequetState) {
        when(state){
            is BibliothequetState.Failure ->{
                makeToast("Error")
            }
            is BibliothequetState.Loading ->{
                makeToast("Loading")
            }
            is BibliothequetState.Success ->{
                Log.d("test","Add all artists ${state.artistes}")
            }
        }
    }

}