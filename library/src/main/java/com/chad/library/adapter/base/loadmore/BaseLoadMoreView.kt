package com.chad.library.adapter.base.loadmore

import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseViewHolder

/**
 *
 * @author limuyang
 */
abstract class BaseLoadMoreView {

    enum class Status {
        Default, Loading, Fail, End
    }

    var loadMoreStatus = Status.Default

    var isLoadEndMoreGone: Boolean = false

    abstract fun getRootView(parent: ViewGroup): View

    abstract fun getLoadingView(holder: BaseViewHolder): View

    abstract fun getLoadEndView(holder: BaseViewHolder): View

    abstract fun getLoadFailView(holder: BaseViewHolder): View

    fun convert(holder: BaseViewHolder) {
        when (loadMoreStatus) {
            Status.Default -> {
                getLoadingView(holder).isVisible(false)
                getLoadFailView(holder).isVisible(false)
                getLoadEndView(holder).isVisible(false)
            }
            Status.Loading -> {
                getLoadingView(holder).isVisible(true)
                getLoadFailView(holder).isVisible(false)
                getLoadEndView(holder).isVisible(false)
            }
            Status.Fail -> {
                getLoadingView(holder).isVisible(false)
                getLoadFailView(holder).isVisible(true)
                getLoadEndView(holder).isVisible(false)
            }
            Status.End -> {
                getLoadingView(holder).isVisible(false)
                getLoadFailView(holder).isVisible(false)
                getLoadEndView(holder).isVisible(true)
            }
        }
    }

    private fun View.isVisible(visible: Boolean) {
        this.visibility = if (visible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}

typealias OnLoadMoreListener = () -> Unit