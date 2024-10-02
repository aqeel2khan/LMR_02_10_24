package com.lmr.mListener

interface PositionItemClickListener<T> {
    fun onPositionItemSelected(item: String, postions: T)

}