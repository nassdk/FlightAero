package com.nassdk.corenetwork.parsing

import kotlinx.serialization.SerialName

data class NetworkErrorResponse(
    @SerialName("error") val errorModel: NetworkErrorModel
)
