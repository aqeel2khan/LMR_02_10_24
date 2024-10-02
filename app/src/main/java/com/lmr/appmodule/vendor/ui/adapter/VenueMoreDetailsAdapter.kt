package com.lmr.appmodule.vendor.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.lmr.BR
import com.lmr.R
import com.lmr.databinding.AdapterVenueMoreDetailsItemBinding
import com.lmr.appmodule.vendor.model.VenueMoreDetailsDataItem

class VenueMoreDetailsAdapter(
    private val mVenueMoreDetailsList: ArrayList<VenueMoreDetailsDataItem>
) : RecyclerView.Adapter<VenueMoreDetailsAdapter.ViewHolder>() {

    inner class ViewHolder(private val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        fun bind(position: Int) {
            val venueMoreDetailsDataItem = mVenueMoreDetailsList[position]
            viewDataBinding.setVariable(BR.venueMoreDetailsDataItem, venueMoreDetailsDataItem)
            viewDataBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val dataBinding = DataBindingUtil.inflate<AdapterVenueMoreDetailsItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.adapter_venue_more_details_item, parent, false)
        return ViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = mVenueMoreDetailsList.size
}