package com.horo.lunchtray.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.horo.lunchtray.model.MenuItem
import com.horo.lunchtray.model.OrderSummary
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LunchTrayViewModel : ViewModel() {
    private var _uiState = MutableStateFlow(OrderSummary())
    val uiState = _uiState.asStateFlow()

    fun updateSelectedItem(selectedItem: MenuItem) {
        when (selectedItem) {
            is MenuItem.EntreeItem -> _uiState.update {
                it.copy(
                    entreeItem = selectedItem
                )
            }

            is MenuItem.SideDishItem -> _uiState.update {
                it.copy(
                    sideDishItem = selectedItem
                )
            }

            is MenuItem.AccompanimentItem -> _uiState.update {
                it.copy(
                    accompanimentItem = selectedItem
                )
            }
        }
        updatePrices()
    }

    fun resetOrder() {
        _uiState.update {
            OrderSummary()
        }
    }

    private fun updatePrices() {
        _uiState.update {
            val subtotal = ((it.entreeItem?.price ?: 0.0)
                    + (it.sideDishItem?.price ?: 0.0)
                    + (it.accompanimentItem?.price ?: 0.0))
            val tax = subtotal * TAX_RATE
            val total = subtotal + tax
            it.copy(
                subtotal = subtotal,
                tax = tax,
                total = total
            )
        }
    }

    companion object {
        const val TAX_RATE = 0.08
    }
}
