package com.android.hikadashi.ui.api

import androidx.lifecycle.*
import com.android.hikadashi.dto.Data
import com.android.hikadashi.model.server.JikanClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApiViewModel() : ViewModel() {
    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState> get() = _state



    init {
        viewModelScope.launch(Dispatchers.Main){
            _state.value = _state.value?.copy(loading = true)
            val getTopAnimes = JikanClient.service.getAiringAnime().data
            _state.value = _state.value?.copy(animes = getTopAnimes)
            _state.value = _state.value?.copy(loading = false)

        }
    }

    fun changeList(text: String){
        viewModelScope.launch(Dispatchers.Main) {
            when (text) {
                "foryou" -> _state.value = _state.value?.copy(animes = JikanClient.service.getRecommendAnimes().data)
                "airing" -> _state.value = _state.value?.copy(animes = JikanClient.service.getAiringAnime().data)
                "upcoming" ->_state.value = _state.value?.copy(animes = JikanClient.service.getUpcomingAnime().data)
                "mostpopular" ->_state.value = _state.value?.copy(animes = JikanClient.service.getTopAnimes().data)

            }
        }
    }

    fun search(name: String){
        viewModelScope.launch(Dispatchers.Main) {
            _state.value =
                _state.value?.copy(animes = JikanClient.service.getSearchAnime(name).data)
        }
    }

    fun navigateTo(anime: Data) {
        _state.value = _state.value?.copy(navigateTo = anime)
    }

    fun onNavigateDone(){
        _state.value = _state.value?.copy(navigateTo = null)
    }

    data class UiState(
        val loading: Boolean = false,
        val animes: List<Data> = emptyList(),
        val navigateTo: Data? = null
    )
}
@Suppress("UNCHECKED_CAST")
class ApiViewModelFactory(): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ApiViewModel() as T
    }
}