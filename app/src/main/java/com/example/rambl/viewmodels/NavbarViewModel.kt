package com.example.rambl.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cornellappdev.android.eateryblue.ui.model.NewsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavbarViewModel @Inject constructor(
    val newsModel: NewsModel
) : ViewModel() {
    sealed class State {
        data class Standard (
            val content : List<String>
        ): State()

        data class Searching (
            var searchContent : String
        ) : State()

        data class RamblOptions (
            val womp : Int
        ) : State()

        data class ProfileScreen (
            val personalAccount : Boolean
        ) : State()

        data class Rambling (
            val content : String,
            val attachments : String
        ) : State()
    }

    private var _state = MutableStateFlow<State>(State.Standard(listOf("Search", "Rambl", "Notification", "News", "Account")))
    val state = _state.asStateFlow()

    fun onSearch(search : String){
        if(_state.value is State.Searching){
            viewModelScope.launch {
                newsModel.search(search)
            }
        } else {
            _state.value = State.Searching("")
        }
    }

    fun onSelectRambl(){
        if(_state.value !is State.RamblOptions) return
        _state.value = State.RamblOptions(1)
    }

    fun onSearchTyped(searchContent : String){
        if(_state.value !is State.Searching) return
        (_state.value as State.Searching).searchContent = searchContent
    }

    fun onProfile(personalAccount: Boolean){
        _state.value = State.ProfileScreen(personalAccount)
    }

    fun onReturn(){
        if(_state.value is State.Standard) return
        _state.value = State.Standard(listOf("Search", "Rambl", "Notification", "News", "Account"))
    }

    fun onRambling(){
        if(_state.value is State.Rambling) return
        _state.value = State.Rambling("","")
    }
}