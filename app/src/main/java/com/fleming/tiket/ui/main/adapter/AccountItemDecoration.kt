package com.fleming.tiket.ui.main.adapter

import android.content.res.Resources
import android.graphics.Rect
import android.view.View

import androidx.recyclerview.widget.RecyclerView
import com.fleming.tiket.R

class AccountItemDecoration(resources: Resources) : RecyclerView.ItemDecoration() {

    private val sideMargin: Int = resources.getDimensionPixelSize(R.dimen.default_side_margin)
    private val verticalMargin: Int = resources.getDimensionPixelSize(R.dimen.default_vertical_margin)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        val position = parent.getChildAdapterPosition(view)
        val lastPosition = parent.childCount - 1

        outRect.left = sideMargin
        outRect.right = sideMargin

        when (position) {
            0 -> {
                outRect.top = verticalMargin
                outRect.bottom = verticalMargin / 2
            }
            lastPosition -> {
                outRect.top = verticalMargin / 2
                outRect.bottom = verticalMargin
            }
            else -> {
                outRect.top = verticalMargin / 2
                outRect.bottom = verticalMargin / 2
            }
        }
    }
}
