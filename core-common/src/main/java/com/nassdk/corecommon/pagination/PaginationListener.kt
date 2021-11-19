package com.nassdk.corecommon.pagination

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber

abstract class PaginationListener(
    private val layoutManager: LinearLayoutManager,
    private val pageSize: Int = DEFAULT_PAGE_SIZE,
) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        Timber.d("PaginationListener onScrolled isLoading ${isLoading()}")
        Timber.d("PaginationListener onScrolled isLastPage ${isLastPage()}")
        if (isLoading() || isLastPage()) return

        val totalItemCount = layoutManager.itemCount
        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
        val updatePosition = totalItemCount - (pageSize / 2)

        Timber.d("PaginationListener onScrolled lastVisibleItemPosition $lastVisibleItemPosition")
        Timber.d("PaginationListener onScrolled updatePosition $updatePosition")

        if (lastVisibleItemPosition >= updatePosition) loadMoreItems()
    }

    protected abstract fun loadMoreItems()
    abstract fun isLastPage(): Boolean
    abstract fun isLoading(): Boolean

    private companion object {

        private const val DEFAULT_PAGE_SIZE = 20
    }
}