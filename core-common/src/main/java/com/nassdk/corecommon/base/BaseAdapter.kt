package com.nassdk.corecommon.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.nassdk.corecommon.extensions.debouncedClick
import com.nassdk.corecommon.extensions.uiLazy

abstract class BaseAdapter<V : ViewBinding, T> :
    RecyclerView.Adapter<BaseAdapter.BaseViewHolder<V>>() {

    private val dataStore by uiLazy {
        mutableListOf<T>()
    }

    abstract val viewBindingInflate: (
        inflater: LayoutInflater,
        viewGroup: ViewGroup,
        attachToParent: Boolean,
    ) -> V

    protected open fun BaseViewHolder<V>.onBind(entity: T) = Unit

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) = BaseViewHolder(
        viewBindingInflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
    )

    override fun onBindViewHolder(holder: BaseViewHolder<V>, position: Int) {
        holder.onBind(dataStore[position])
    }

    override fun getItemCount(): Int = dataStore.size

    fun add(vararg items: T) {

        val startUpdatePosition = dataStore.lastIndex
        dataStore.addAll(items.asList())
        notifyItemRangeInserted(startUpdatePosition, items.size)
    }

    fun add(items: List<T>) {

        val startUpdatePosition = dataStore.lastIndex
        dataStore.addAll(items)
        notifyItemRangeInserted(startUpdatePosition, items.size)
    }

    fun addWithClear(vararg items: T) {

        val startUpdatePosition = dataStore.lastIndex

        dataStore.clear()
        dataStore.addAll(items.asList())

        notifyItemRangeChanged(startUpdatePosition, items.size, null)
    }

    fun addWithClear(items: List<T>) {

        val startUpdatePosition = dataStore.lastIndex

        dataStore.clear()
        dataStore.addAll(items)

        notifyItemRangeChanged(startUpdatePosition, items.size, null)
    }

    fun getItemBy(position: Int): T? {
        return dataStore.getOrNull(position)
    }

    fun updateItemBy(position: Int, item: T) {

        dataStore.getOrNull(position) ?: return

        dataStore[position] = item
        notifyItemChanged(position, null)
    }

    fun clear() {

        dataStore.clear()
        notifyDataSetChanged() //FIXME
    }

    class BaseViewHolder<V : ViewBinding>(
        val itemBinding: V,
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        val context: Context
            get() = itemBinding.root.context

        fun onItemClick(onClick: () -> Unit) {
            itemBinding.root.debouncedClick { onClick.invoke() }
        }
    }

    private companion object {
        private const val DEFAULT_PAGE_LIMIT = 20

        private const val ORDINAL_HOLDER_VIEW_TYPE = 2222
        private const val LOADING_HOLDER_VIEW_TYPE = 1111
    }
}
