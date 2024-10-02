package com.lmr.appmodule.search.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lmr.databinding.EventRowBinding

//import com.lmr.databinding.EventRowBinding


class SearchEventViewHolder (
    private val binding: EventRowBinding,
  //  private val listener: ItemClickListener<Int>,
    private val adapter: SearchEventAdapter
): RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bindData(/*data: ListItemNew, position: Int*/) {
      /*  binding.name.text= data.name
        binding.stateName.text= data.state
        binding.rating.text= data.totalGood
        binding.revivew.text= data.totalBad
        binding.exp.text= (data.exp_year?:"0")+" "+binding.root.context.getString(R.string.year_exp)
        binding.jobComplete.text= (data.totalJob?:"0")+" "+binding.root.context.getString(R.string.job_complete)
        Glide.with(binding.root.context)
            .load(MyConstant.IMAGE_BASE_URL+data.profilePic)
            .circleCrop()
            .placeholder(R.drawable.empty_image)
            .into(binding.image)
        binding.root.setOnClickListener {
            listener.onItemSelected(data.id.toString(),position)
        }*/

    }

}