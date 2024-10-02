package com.lmr.appmodule.vendor.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.lmr.BR
import com.lmr.R
import com.lmr.databinding.AdapterVendorPoliciesItemBinding
import com.lmr.appmodule.vendor.model.VendorPolicyDataItem

class VenueCostDetailsAdapter(
    private val mVendorPoliciesList: ArrayList<VendorPolicyDataItem>
) : RecyclerView.Adapter<VenueCostDetailsAdapter.ViewHolder>() {

    inner class ViewHolder(private val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        fun bind(position: Int) {
            val vendorPolicyDataItem = mVendorPoliciesList[position]
            viewDataBinding.setVariable(BR.vendorPolicyDataItem, vendorPolicyDataItem)
            viewDataBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val dataBinding = DataBindingUtil.inflate<AdapterVendorPoliciesItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.adapter_vendor_policies_item, parent, false)
        return ViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = mVendorPoliciesList.size
}