package com.lmr.appmodule.search.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lmr.databinding.EventRowBinding

class SearchEventAdapter(/*private var list: List<ListItemNew?>, private val listener: ItemClickListener<Int>*/)
    : RecyclerView.Adapter<SearchEventViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): SearchEventViewHolder {
        return SearchEventViewHolder(EventRowBinding.inflate(LayoutInflater.from(parent.context),parent,false),this)
    }
    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: SearchEventViewHolder, position: Int) {
       // list[position]?.let { holder.bindData(it,position) }
        holder.bindData()
    }
    /*
        @SuppressLint("NotifyDataSetChanged")
        fun filterList(filterList: ArrayList<CategoryListItem?>): ArrayList<CategoryListItem?> {
            list = filterList
            notifyDataSetChanged()
            return list
        }*/
}