package com.horo.dessertclicker

import androidx.lifecycle.ViewModel
import com.horo.dessertclicker.data.DataSource.dessertList
import com.horo.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DessertUiState())

    val uiState = _uiState.asStateFlow()

    fun onDessertClick() {
        val updatedDessertSold = _uiState.value.dessertSold.inc()
        val dessertToShow = getDessertToShow(dessertList, updatedDessertSold)
        _uiState.update {
            it.copy(
                dessertSold = updatedDessertSold,
                revenue = it.revenue + it.currentPrice,
                imageRes = dessertToShow.imageRes,
                currentPrice = dessertToShow.price,
                currentDessertIndex = dessertList.indexOf(dessertToShow)
            )
        }
    }

    fun getDessertToShow(dessertList: List<Dessert>, backerSold: Int): Dessert {
        var dessertToShow = dessertList.first()
        for (dessert in dessertList) {
            if (backerSold >= dessert.startAmountProduction) {
                dessertToShow = dessert
            } else {
                break
            }
        }

        return dessertToShow
    }
}
