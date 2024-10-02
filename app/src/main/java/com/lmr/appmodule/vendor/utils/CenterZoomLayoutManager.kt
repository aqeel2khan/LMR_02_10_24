package com.lmr.appmodule.vendor.utils

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs
import kotlin.math.min

class CenterZoomLayoutManager(context: Context) : LinearLayoutManager(context, HORIZONTAL, false) {

    private val shrinkAmount = 0.15f
    private val shrinkDistance = 0.9f

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
        val midpoint = width / 2.0f
        val d0 = 0.0f
        val d1 = shrinkDistance * midpoint
        val s0 = 1.0f
        val s1 = 1.0f - shrinkAmount
        for (i in 0 until childCount) {
            val child = getChildAt(i)!!
            val childMidpoint = (getDecoratedRight(child) + getDecoratedLeft(child)) / 2.0f
            val d = min(d1, abs(midpoint - childMidpoint))
            val scale = s0 + (s1 - s0) * (d - d0) / (d1 - d0)
            child.scaleX = scale
            child.scaleY = scale
        }
        return scrolled
    }
}