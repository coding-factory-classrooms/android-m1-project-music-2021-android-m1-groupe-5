package com.cheel.project_music.ui.login.viewmodel.interfaces

import androidx.lifecycle.LiveData
import com.cheel.project_music.ui.login.viewmodel.LoginFragmentState

interface ILoginViewModel {
    fun getState() : LiveData<LoginFragmentState>
    fun connection(userName : String, password: String)
}