package com.lmr.appmodule.organizerDetail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lmr.databinding.ItemEventBinding


class EventAdapter(
    private var mContext:Context
    ) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemEventBinding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding,mContext)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(position)
    }

    override fun getItemCount(): Int {
        return 8
    }




    inner class ViewHolder(val binding: ItemEventBinding, val mContext: Context) : RecyclerView.ViewHolder(binding.root) {

        fun bind( position: Int) {


        }

    }

}
