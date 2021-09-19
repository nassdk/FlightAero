package com.nassdk.corenetwork.parsing

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class NetworkErrorParser @Inject constructor(
    private val json: Json
) {
    fun parseError(response: String?): NetworkErrorModel? = try {
        json.decodeFromString<NetworkErrorModel>(response!!)
    } catch (e: Exception) {
        null
    }
}