package com.nassdk.corecommon.resourcemanager

import android.content.Context
import javax.inject.Inject

class ResourceManagerImpl @Inject constructor(
    private val context: Context
) : ResourceManager {

    override fun getString(resourceId: Int): String {
        return context.getString(resourceId)
    }
}