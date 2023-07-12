package com.mungai.androidcomposegraphql.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mungai.androidcomposegraphql.domain.Character
import com.mungai.androidcomposegraphql.domain.CharactersClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersClient: CharactersClient
) : ViewModel() {

    private val _state = MutableStateFlow(CharactersState())
    val state = _state.asStateFlow()

    data class CharactersState(
        val characters: List<Character> = emptyList(),
        val isLoading: Boolean = false
    )

    init {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            _state.update {
                it.copy(
                    characters = charactersClient.getCharacters(),
                    isLoading = false
                )
            }
        }
    }
}