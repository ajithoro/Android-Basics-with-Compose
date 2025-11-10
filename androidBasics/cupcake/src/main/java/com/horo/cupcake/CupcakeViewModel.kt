package com.horo.cupcake

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.horo.cupcake.model.CupcakeUiState

class CupcakeViewModel : ViewModel() {
    var _uiState = mutableStateOf(CupcakeUiState())
}
