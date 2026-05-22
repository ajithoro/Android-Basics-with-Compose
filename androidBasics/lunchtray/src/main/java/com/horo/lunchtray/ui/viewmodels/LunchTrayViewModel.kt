package com.horo.lunchtray.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.horo.lunchtray.model.OrderSummary
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LunchTrayViewModel : ViewModel() {
    private var _uiState = MutableStateFlow(OrderSummary())
    val uiState = _uiState.asStateFlow()
}
