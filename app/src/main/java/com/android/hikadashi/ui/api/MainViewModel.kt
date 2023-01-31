package com.android.hikadashi.ui.api

import androidx.lifecycle.*
import com.android.hikadashi.dto.anime.Anime
import com.android.hikadashi.model.server.JikanClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApiViewModel() : ViewModel() {
    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState> get() = _state


    init {
        viewModelScope.launch(Dispatchers.Main){
            _state.value = _state.value?.copy(loading = true)
            val getTopAnimes = JikanClient.service.getTopAnimes()
            _state.value = _state.value?.copy(animes = getTopAnimes)
            _state.value = _state.value?.copy(loading = false)

        }
    }

    fun navigateTo(anime: Anime) {
        _state.value = _state.value?.copy(navigateTo = anime)
    }

    fun onNavigateDone(){
        _state.value = _state.value?.copy(navigateTo = null)
    }

    data class UiState(
        val loading: Boolean = false,
        val animes: List<Anime> = emptyList(),
        val navigateTo: Anime? = null
    )
}
@Suppress("UNCHECKED_CAST")
class ApiViewModelFactory(): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ApiViewModel() as T
    }
}