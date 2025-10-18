package com.horo.marsphotos.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.horo.marsphotos.network.MarsApi
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

sealed interface MarsUiState {
    object Loading : MarsUiState
    data class Success(val photos: String) : MarsUiState
    object Error : MarsUiState
}

class MarsViewModel : ViewModel() {
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set

    init {
        getPhotoList()
    }

    fun getPhotoList() {
        viewModelScope.launch {
            marsUiState = MarsUiState.Loading
            marsUiState = try {
                val photoList = MarsApi.marsApiService.getPhotoList()
                MarsUiState.Success(photos = "Total photos: ${photoList.size}")
            } catch (e: IOException) {
                MarsUiState.Error
            } catch (e: HttpException) {
                MarsUiState.Error
            }
        }
    }

}
