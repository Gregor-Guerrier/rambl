package com.example.rambl.viewmodels

import androidx.lifecycle.ViewModel
import com.example.rambl.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {
    sealed class State (
        open var account : Account
    ){
        data class Account(
            var username : String,
            var alias : String,
            var followers : Int,
            var following : Int
        )
        data class Badges (
            var badges : List<Int>,
            override var account : Account
        ) : State(account)

        data class Profile (
            var posts : List<String>,
            override var account : Account
        ) : State(account)

    }

    private val _state : MutableStateFlow<State> = MutableStateFlow(State.Profile(listOf(), State.Account("@merkt", "Gregor Guerrier", 1000, 1000)))

    val profileState = _state.asStateFlow()

    fun onShowBadges(){
        val currState = _state.value
        _state.value = State.Badges(listOf(R.drawable.verified, R.drawable.premium), currState.account)
    }

    fun onShowProfile(){
        val currState = _state.value
        _state.value = State.Profile(listOf("Hello World!"), currState.account)
    }




}