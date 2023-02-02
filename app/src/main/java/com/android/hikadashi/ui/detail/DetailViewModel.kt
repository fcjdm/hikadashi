package com.android.hikadashi.ui.detail

import androidx.lifecycle.*
import com.android.hikadashi.dto.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailMatchViewModel(anime: Data) : ViewModel() {
    private val _state = MutableLiveData(UiState(anime))
    val state: LiveData<UiState> get() = _state


    init{
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = _state.value?.copy(anime = anime)

        }
    }

    data class UiState(
        val anime: Data
    )
}
@Suppress("UNCHECKED_CAST")
class DetailMatchViewModelFactory(private val anime: Data): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailMatchViewModel(anime) as T
    }

}