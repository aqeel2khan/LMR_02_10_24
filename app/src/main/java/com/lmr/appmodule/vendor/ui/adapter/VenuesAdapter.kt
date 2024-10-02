package com.lmr.appmodule.vendor.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.lmr.BR
import com.lmr.R
import com.lmr.databinding.AdapterVenueItemBinding
import com.lmr.appmodule.vendor.model.VenuesDataItem
import com.lmr.appmodule.vendor.utils.OnClickListener

class VenuesAdapter(
    private val mVenuesList: ArrayList<VenuesDataItem>,
    private val mClickListener: OnClickListener
) : RecyclerView.Adapter<VenuesAdapter.ViewHolder>() {

    inner class ViewHolder(private val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        fun bind(position: Int) {
            val venuesDataItem = mVenuesList[position]
            viewDataBinding.setVariable(BR.venuesDataItem, venuesDataItem)
            viewDataBinding.setVariable(BR.clickListener, mClickListener)
            viewDataBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val dataBinding = DataBindingUtil.inflate<AdapterVenueItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.adapter_venue_item, parent, false)
        return ViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = mVenuesList.size
}