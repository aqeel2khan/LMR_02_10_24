package com.lmr.appmodule.eventorganizorlist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lmr.R
//import com.lmr.app_utils.OnClickListener
import com.lmr.appmodule.eventorganizorlist.model.OrganizerDataItem
import com.lmr.appmodule.vendor.utils.OnClickListener
import com.lmr.databinding.AdapterOranizerListItemBinding

class EventOrganizerListAdapter(
    private val mOganizereventList: ArrayList<OrganizerDataItem>,
    private val mClickListener: OnClickListener,
    private val mContext:Context
) : RecyclerView.Adapter<EventOrganizerListAdapter.ViewHolder>() {

    inner class ViewHolder(private val viewDataBinding: AdapterOranizerListItemBinding) :

        RecyclerView.ViewHolder(viewDataBinding.root) {
        fun bind(data: OrganizerDataItem, position: Int) {
            viewDataBinding.tvBankName.text = data.bankName
            viewDataBinding.tvAddress.text =data.bankAddress.toString()

            Glide.with(mContext)
                .load(data.backGroundImage)
                .into(viewDataBinding.ivBackground);
//            binding.txtProfesion.text = "Yoga, pilates"
//            MyUtils.loadImage(
//                binding.imgTrainerProfile,
//                MyConstant.IMAGE_BASE_URL + data.profilePhotoPath
//            )
//
            viewDataBinding.outerLayout.setOnClickListener {
                mClickListener.onClick(viewDataBinding.outerLayout, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val dataBinding = DataBindingUtil.inflate<AdapterVendorVenueItemBinding>(
//            LayoutInflater.from(parent.context),
//            R.layout.adapter_oranizer_list_item, parent, false)

        val binding =
            AdapterOranizerListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(position)
        holder.bind(mOganizereventList[position], position)
    }


    override fun getItemCount(): Int = mOganizereventList.size
}