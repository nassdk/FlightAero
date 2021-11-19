package com.nassdk.corenetwork

interface NetworkApi {

    fun <T> provideApiClass(interfaceClass: Class<T>): T
}