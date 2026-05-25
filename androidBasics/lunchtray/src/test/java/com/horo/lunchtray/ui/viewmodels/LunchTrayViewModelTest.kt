package com.horo.lunchtray.ui.viewmodels

import com.horo.lunchtray.data.DataSource
import com.horo.lunchtray.model.OrderSummary
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

class LunchTrayViewModelTest {

    lateinit var viewModel: LunchTrayViewModel

    @Before
    fun setUp() {
        viewModel = LunchTrayViewModel()
    }

    @Test
    fun `should update entree item`() {
        viewModel.updateSelectedItem(DataSource.entreeMenuItems.first())

        assertEquals(viewModel.uiState.value.entreeItem, DataSource.entreeMenuItems.first())
    }

    @Test
    fun `should update side dish item`() {
        viewModel.updateSelectedItem(DataSource.sideDishMenuItems.first())

        assertEquals(viewModel.uiState.value.sideDishItem, DataSource.sideDishMenuItems.first())
    }

    @Test
    fun `should update accompaniment item`() {
        viewModel.updateSelectedItem(DataSource.accompanimentMenuItems.first())

        assertEquals(
            viewModel.uiState.value.accompanimentItem,
            DataSource.accompanimentMenuItems.first()
        )
    }

    @Test
    fun `should reset order`() {
        viewModel.resetOrder()

        assertEquals(viewModel.uiState.value, OrderSummary())
    }

    @Test
    fun `should update prices upon updating of entree item`() {
        val initialSubtotal = viewModel.uiState.value.subtotal
        val initialTax = viewModel.uiState.value.tax
        val initialTotal = viewModel.uiState.value.total
        viewModel.updateSelectedItem(DataSource.entreeMenuItems.last())

        assertNotEquals(initialSubtotal, viewModel.uiState.value.subtotal)
        assertNotEquals(initialTax, viewModel.uiState.value.tax)
        assertNotEquals(initialTotal, viewModel.uiState.value.total)
    }

    @After
    fun tearDown() {

    }

}
