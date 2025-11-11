package com.horo.cupcake

import androidx.lifecycle.ViewModel
import com.horo.cupcake.model.CupcakeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CupcakeViewModel : ViewModel() {
    var _uiState = MutableStateFlow(CupcakeUiState())
        private set
    val uiState = _uiState.asStateFlow()

    fun updateFlavor(flavor: String) {
        _uiState.update {
            it.copy(
                flavor = flavor
            )
        }
    }

    fun updateQuantity(quantity: Int) {
        _uiState.update {
            it.copy(
                quantity = quantity
            )
        }
    }

    fun updatePickUpDate(pickUpDate: String) {
        _uiState.update {
            it.copy(
                pickUpDate = pickUpDate
            )
        }
    }
}
