package com.android.hikadashi.ui.detail

import androidx.lifecycle.*
import com.android.hikadashi.dto.Data
import com.android.hikadashi.model.db.DbFirestore
import com.android.hikadashi.model.server.JikanClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailMatchViewModel(anime: Data) : ViewModel() {
    private val _state = MutableLiveData(UiState(anime))
    val state: LiveData<UiState> get() = _state
    val status: String = ""


    init{
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = _state.value?.copy(anime = anime)
            _state.value = _state.value?.copy(loading = true)
            _state.value = _state.value?.copy(status = DbFirestore.getStatus(_state.value!!.anime.malId))
            _state.value = _state.value?.copy(loading = false)


        }
    }

    fun addAnime(){
        viewModelScope.launch(Dispatchers.Main) {
            _state.value?.anime?.let { DbFirestore.addAnime(it.malId, "WATCHING") }
            _state.value = _state.value?.copy(status = DbFirestore.getStatus(_state.value!!.anime.malId))
        }
    }

    fun changeStatus(status: String){

    }

    data class UiState(
        val anime: Data,
        val status: String = "",
        val loading: Boolean = false
    )
}
@Suppress("UNCHECKED_CAST")
class DetailMatchViewModelFactory(private val anime: Data): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailMatchViewModel(anime) as T
    }

}