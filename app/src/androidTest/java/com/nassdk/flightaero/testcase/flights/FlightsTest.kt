package com.nassdk.flightaero.testcase.flights

import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.nassdk.flightaero.scenario.OpenAppActivity
import com.nassdk.flightaero.screen.flights.FlightsScreen
import org.junit.Test

internal class FlightsTest : TestCase() {

    @Test
    fun test() {
        before {

        }.after {

        }.run {
            step(description = "Запуск прилаги") {
                scenario(OpenAppActivity())
            }
            step(description = "Открыть экран Списка перелетов") {
                FlightsScreen.shimmerLoading.isVisible()
            }
        }
    }
}