package com.lmr.appmodule.vendor.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lmr.BR
import com.lmr.R
import com.lmr.databinding.AdapterVendorItemBinding
import com.lmr.appmodule.vendor.model.VendorDataItem
import com.lmr.appmodule.vendor.utils.OnClickListener

class HomeVendorAdapter(
    private val mVendorList: ArrayList<VendorDataItem>,
    private val mClickListener: OnClickListener
) : RecyclerView.Adapter<HomeVendorAdapter.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    inner class ViewHolder(private val viewDataBinding: AdapterVendorItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        fun bind(position: Int) {
            val vendorDataItem = mVendorList[position]
            viewDataBinding.rcvVendorsList.layoutManager = LinearLayoutManager(viewDataBinding.rcvVendorsList.context, LinearLayoutManager.HORIZONTAL, false)
            if (vendorDataItem.vendorType.equals("Venue")) {
                viewDataBinding.rcvVendorsList.adapter =
                    vendorDataItem.vendorEventsList?.let { VendorVenueAdapter(it, mClickListener) }
            } else if (vendorDataItem.vendorType.equals("Vendor")) {
                viewDataBinding.rcvVendorsList.adapter =
                    vendorDataItem.vendorEventsList?.let { VendorCategoryAdapter(it, mClickListener) }
            } else if (vendorDataItem.vendorType.equals("Tools")) {
                viewDataBinding.rcvVendorsList.adapter =
                    vendorDataItem.vendorEventsList?.let { VendorVenueAdapter(it, mClickListener) }
            } else if (vendorDataItem.vendorType.equals("Filming")) {
                viewDataBinding.rcvVendorsList.adapter =
                    vendorDataItem.vendorEventsList?.let { VendorVenueAdapter(it, mClickListener) }
            }
            viewDataBinding.rcvVendorsList.setRecycledViewPool(viewPool)
            viewDataBinding.setVariable(BR.vendorDataItem, vendorDataItem)
            viewDataBinding.setVariable(BR.clickListener, mClickListener)
            viewDataBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val dataBinding = DataBindingUtil.inflate<AdapterVendorItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.adapter_vendor_item, parent, false)
        return ViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = mVendorList.size
}