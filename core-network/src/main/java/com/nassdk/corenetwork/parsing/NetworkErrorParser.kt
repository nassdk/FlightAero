package com.nassdk.corenetwork.parsing

import dagger.Reusable
import javax.inject.Inject
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Reusable
class NetworkErrorParser @Inject constructor(
    private val json: Json
) {
    fun parseError(response: String?): NetworkErrorModel? = try {
        val errorResponse = json.decodeFromString<NetworkErrorResponse>(response!!)
        errorResponse.errorModel
    } catch (e: Exception) {
        null
    }
}