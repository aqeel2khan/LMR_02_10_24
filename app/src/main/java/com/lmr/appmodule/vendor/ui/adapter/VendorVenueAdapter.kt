package com.lmr.appmodule.vendor.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.lmr.BR
import com.lmr.R
import com.lmr.databinding.AdapterVendorVenueItemBinding
import com.lmr.appmodule.vendor.model.VendorEventDataItem
import com.lmr.appmodule.vendor.utils.OnClickListener

class VendorVenueAdapter(
    private val mVendorEventsList: ArrayList<VendorEventDataItem>,
    private val mClickListener: OnClickListener
) : RecyclerView.Adapter<VendorVenueAdapter.ViewHolder>() {

    inner class ViewHolder(private val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        fun bind(position: Int) {
            val vendorEventDataItem = mVendorEventsList[position]
            viewDataBinding.setVariable(BR.vendorEventDataItem, vendorEventDataItem)
            viewDataBinding.setVariable(BR.clickListener, mClickListener)
            viewDataBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val dataBinding = DataBindingUtil.inflate<AdapterVendorVenueItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.adapter_vendor_venue_item, parent, false)
        return ViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = mVendorEventsList.size
}