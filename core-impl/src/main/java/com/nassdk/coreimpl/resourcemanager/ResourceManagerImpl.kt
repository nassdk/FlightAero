package com.nassdk.coreimpl.resourcemanager

import android.content.Context
import com.nassdk.coreapi.resourcemanager.ResourceManager
import javax.inject.Inject

class ResourceManagerImpl @Inject constructor(
    private val context: Context
) : ResourceManager {

    override fun getString(resourceId: Int): String {
        return context.getString(resourceId)
    }
}