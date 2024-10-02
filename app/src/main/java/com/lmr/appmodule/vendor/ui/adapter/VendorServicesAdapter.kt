package com.lmr.appmodule.vendor.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.lmr.BR
import com.lmr.R
import com.lmr.databinding.AdapterVendorServicesItemBinding
import com.lmr.appmodule.vendor.model.VendorServiceDataItem
import com.lmr.appmodule.vendor.utils.OnClickListener

class VendorServicesAdapter(
    private val mVendorServicesList: ArrayList<VendorServiceDataItem>,
    private val mClickListener: OnClickListener
) : RecyclerView.Adapter<VendorServicesAdapter.ViewHolder>() {

    inner class ViewHolder(private val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        fun bind(position: Int) {
            val vendorServiceDataItem = mVendorServicesList[position]
            viewDataBinding.setVariable(BR.vendorServiceDataItem, vendorServiceDataItem)
            viewDataBinding.setVariable(BR.clickListener, mClickListener)
            viewDataBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val dataBinding = DataBindingUtil.inflate<AdapterVendorServicesItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.adapter_vendor_services_item, parent, false)
        return ViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = mVendorServicesList.size
}