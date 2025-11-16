package com.horo.cupcake.test

import androidx.navigation.NavController
import org.junit.Assert.assertEquals

fun NavController.assertCurrentRouteName(expectedScreenName: String) {
    assertEquals(expectedScreenName, currentBackStackEntry?.destination?.route)
}
