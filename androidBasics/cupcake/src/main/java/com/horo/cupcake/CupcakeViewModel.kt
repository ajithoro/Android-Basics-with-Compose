package com.horo.cupcake

import android.os.Build
import androidx.lifecycle.ViewModel
import com.horo.cupcake.model.CupcakeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class CupcakeViewModel : ViewModel() {
    private var _uiState = MutableStateFlow(CupcakeUiState(pickUpOptionList = getPickUpOptions()))
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
                quantity = quantity,
                subTotal = calculatePrice(quantity)
            )
        }
    }

    fun updatePickUpDate(pickUpDate: String) {
        _uiState.update {
            it.copy(
                pickUpDate = pickUpDate,
                subTotal = calculatePrice(uiState.value.quantity, pickUpDate = pickUpDate)
            )
        }
    }

    fun calculatePrice(quantity: Int, pickUpDate: String = uiState.value.pickUpDate): String {
        var subtotal = quantity * PRICE_PER_CUPCAKE
        if (getPickUpOptions()[0] == uiState.value.pickUpDate) {
            subtotal += PRICE_SAME_DAY_PICK_UP
        }

        return NumberFormat.getCurrencyInstance().format(subtotal)
    }

    fun getPickUpOptions(): List<String> {
        var localDate = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDate.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val formatter = DateTimeFormatter.ofPattern("E MMM d", Locale.getDefault())
        val optionList = mutableListOf<String>()
        repeat(4) {
            optionList.add(localDate.format(formatter))
            localDate = localDate.plusDays(1)
        }

        return optionList
    }

    fun resetOrder() {
        _uiState.update {
            CupcakeUiState(pickUpOptionList = getPickUpOptions())
        }
    }

    companion object {
        const val PRICE_PER_CUPCAKE = 2.0
        const val PRICE_SAME_DAY_PICK_UP = 3.0
    }
}
