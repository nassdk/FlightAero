package com.nassdk.corecommon.error

import com.nassdk.corecommon.R
import com.nassdk.corecommon.resourcemanager.ResourceManager
import dagger.Reusable
import javax.inject.Inject

@Reusable
class UiErrorHandler @Inject constructor(
    private val errorHandler: ErrorHandler,
    private val resourceManager: ResourceManager
) {
    fun proceedError(throwable: Throwable, errorListener: (String) -> Unit) {

        val message = when (val error = errorHandler.getError(throwable = throwable)) {
            ErrorWrapper.ServerError -> resourceManager.getString(R.string.error_server_internal)
            ErrorWrapper.Timeout -> resourceManager.getString(R.string.error_server_timeout)
            ErrorWrapper.Network -> resourceManager.getString(R.string.error_server_network)
            ErrorWrapper.Unknown -> resourceManager.getString(R.string.error_server_unknown)
            is ErrorWrapper.CustomError -> error.message
        }

        errorListener.invoke(message)
    }
}