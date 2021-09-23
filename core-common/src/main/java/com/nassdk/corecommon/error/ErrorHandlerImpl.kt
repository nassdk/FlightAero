package com.nassdk.corecommon.error

import com.nassdk.corenetwork.parsing.NetworkErrorParser
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

class ErrorHandlerImpl @Inject constructor(
    private val errorParser: NetworkErrorParser
) : ErrorHandler {

    override fun getError(throwable: Throwable): ErrorWrapper = when (throwable) {
        is IOException -> ErrorWrapper.Network
        is TimeoutException -> ErrorWrapper.Timeout
        is HttpException -> {
            when (throwable.code()) {
                in 400..499 -> {
                    val response = throwable.response()?.errorBody()?.string()
                    val errorNetModel = errorParser.parseError(response)

                    if (errorNetModel != null)
                        ErrorWrapper.CustomError(message = errorNetModel.message)
                    else ErrorWrapper.Unknown
                }
                in 500..599 -> ErrorWrapper.ServerError
                else -> ErrorWrapper.Unknown
            }
        }
        else -> ErrorWrapper.Unknown
    }
}