package com.nassdk.flightaero.mediators

object MediatorManager {
    val splashMediator: SplashMediator by lazy { SplashMediator() }
    val flowMediator: FlowMediator by lazy { FlowMediator() }
    val flightsMediator: FlightsMediator by lazy { FlightsMediator() }
    val profileMediator: ProfileMediator by lazy { ProfileMediator() }
    val networkMediator: NetworkMediator by lazy { NetworkMediator() }
}