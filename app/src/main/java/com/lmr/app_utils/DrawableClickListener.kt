package com.lmr.app_utils


interface DrawableClickListener {

    enum class DrawablePosition {
        TOP, BOTTOM, START, END
    }

    fun onClick(target: DrawablePosition)
}