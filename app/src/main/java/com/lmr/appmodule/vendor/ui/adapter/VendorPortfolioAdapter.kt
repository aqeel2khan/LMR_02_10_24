package com.lmr.appmodule.vendor.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.lmr.BR
import com.lmr.R
import com.lmr.databinding.AdapterVendorPortfolioItemBinding
import com.lmr.appmodule.vendor.model.VendorPortfolioDataItem
import com.lmr.appmodule.vendor.utils.OnClickListener

class VendorPortfolioAdapter(
    private val mVendorPortfolioList: ArrayList<VendorPortfolioDataItem>,
    private val mClickListener: OnClickListener
) : RecyclerView.Adapter<VendorPortfolioAdapter.ViewHolder>() {

    inner class ViewHolder(private val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        fun bind(position: Int) {
            val vendorPortfolioDataItem = mVendorPortfolioList[position]
            viewDataBinding.setVariable(BR.vendorPortfolioDataItem, vendorPortfolioDataItem)
            viewDataBinding.setVariable(BR.clickListener, mClickListener)
            viewDataBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val dataBinding = DataBindingUtil.inflate<AdapterVendorPortfolioItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.adapter_vendor_portfolio_item, parent, false)
        return ViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = mVendorPortfolioList.size
}