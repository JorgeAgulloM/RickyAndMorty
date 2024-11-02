package org.example.rickyandmorty.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.example.rickyandmorty.domain.Repository
import org.example.rickyandmorty.domain.model.CharacterModel

class CharacterViewModel(
    characterModel: CharacterModel,
    private val repository: Repository
): ViewModel() {
    private val _uiState = MutableStateFlow(CharacterDetailState(characterModel))
    val uiState: StateFlow<CharacterDetailState> = _uiState.asStateFlow()

    fun getEpisodesForCharacter(episodes: List<String>) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getEpisodeForCharacter(episodes)
            }

            _uiState.update { it.copy(episodes = result) }
        }
    }

}