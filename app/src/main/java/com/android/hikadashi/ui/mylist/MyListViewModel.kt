package com.android.hikadashi.ui.mylist

import androidx.lifecycle.*
import com.android.hikadashi.dto.Data
import com.android.hikadashi.model.db.DbFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyListViewModel: ViewModel() {
    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState> get() = _state



    init {
        viewModelScope.launch(Dispatchers.Main){
            _state.value = _state.value?.copy(loading = true)
            val getAnimesWatching = DbFirestore.getByStatus("WATCHING")
            _state.value = _state.value?.copy(animes = getAnimesWatching)
            _state.value = _state.value?.copy(loading = false)

        }
    }

    fun changeList(text: String){
        viewModelScope.launch(Dispatchers.Main) {
            when (text) {
                "WATCHING" -> {
                    _state.value = _state.value?.copy(loading = true)
                    _state.value = _state.value?.copy(animes = DbFirestore.getByStatus("WATCHING"))
                    _state.value = _state.value?.copy(loading = false)
                }
                "PLAN TO WATCH" ->{
                    _state.value = _state.value?.copy(loading = true)
                    _state.value = _state.value?.copy(animes = DbFirestore.getByStatus("PLAN TO WATCH"))
                    _state.value = _state.value?.copy(loading = false)
                }
                "ON HOLD" ->{
                    _state.value = _state.value?.copy(loading = true)
                    _state.value = _state.value?.copy(animes = DbFirestore.getByStatus("ON HOLD"))
                    _state.value = _state.value?.copy(loading = false)
                }
                "DROPPED" ->{
                    _state.value = _state.value?.copy(loading = true)
                    _state.value = _state.value?.copy(animes = DbFirestore.getByStatus("DROPPED"))
                    _state.value = _state.value?.copy(loading = false)
                }

            }
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
class MyListViewModelFactory(): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyListViewModel() as T
    }
}