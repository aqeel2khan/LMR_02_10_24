package com.lmr.appmodule.vendor.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.lmr.BR
import com.lmr.R
import com.lmr.databinding.AdapterVenueAvailableAreaItemBinding
import com.lmr.appmodule.vendor.model.VenueAvailableAreaDataItem

class VenueAvailableAreaAdapter(
    private val mVenueAvailableAreaList: ArrayList<VenueAvailableAreaDataItem>
) : RecyclerView.Adapter<VenueAvailableAreaAdapter.ViewHolder>() {

    inner class ViewHolder(private val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        fun bind(position: Int) {
            val venueAvailableAreaDataItem = mVenueAvailableAreaList[position]
            viewDataBinding.setVariable(BR.venueAvailableAreaDataItem, venueAvailableAreaDataItem)
            viewDataBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val dataBinding = DataBindingUtil.inflate<AdapterVenueAvailableAreaItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.adapter_venue_available_area_item, parent, false)
        return ViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = mVenueAvailableAreaList.size
}