package com.cornellappdev.android.eateryblue.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class HomeViewModel(
) : ViewModel() {

    sealed class State {
        data class Unselected(
            val idk : String
        ) : State()
        data class Selected(
            val userId : Int
        ) : State()
    }

    private val _ramblFlow: MutableStateFlow<State> = MutableStateFlow(State.Unselected(""))

    /**
     * A flow of filters applied to the screen.
     */
    val ramblFLow = _ramblFlow.asStateFlow()

    fun unselectMessage(){
        _ramblFlow.value = State.Unselected("")
    }

    fun selectMessage(id : Int){
        _ramblFlow.value = State.Selected(id)
    }

}