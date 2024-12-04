package com.lmr.appmodule.eventorganizorlist.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
//import com.lmr.app_utils.OnClickListener
import com.lmr.appmodule.home.model.Organizer
import com.lmr.appmodule.organizerDetail.CompanyDetailsFragment
import com.lmr.databinding.AdapterOranizerListItemBinding

class EventOrganizerListAdapter(
    private val mOganizereventList: List<Organizer>,
    private val mContext:Context
) : RecyclerView.Adapter<EventOrganizerListAdapter.ViewHolder>() {

    inner class ViewHolder(private val viewDataBinding: AdapterOranizerListItemBinding) :

        RecyclerView.ViewHolder(viewDataBinding.root) {
        fun bind(data: Organizer, position: Int) {
            viewDataBinding.tvBankName.text = data.eventOrganizerName
            viewDataBinding.tvAddress.text =data.organizerAddress.toString()
            viewDataBinding.tvEventCount.text =data.eventCount.toString()

            Glide.with(mContext)
                .load(data.profileImage)
                .into(viewDataBinding.ivBackground);

            viewDataBinding.root.setOnClickListener {
               // mContext.startActivity(Intent(mContext, CompanyDetailsFragment::class.java)

                val intent = Intent(mContext, CompanyDetailsFragment::class.java).apply {
                putExtra("EXTRA_ID", data.eventOrganizerID.toString())
                putExtra("EXTRA_COMPANY_NAME", data.organizerAddress)
            }
                mContext.startActivity(intent)

//
            }
//            binding.txtProfesion.text = "Yoga, pilates"
//            MyUtils.loadImage(
//                binding.imgTrainerProfile,
//                MyConstant.IMAGE_BASE_URL + data.profilePhotoPath
//            )
//
            /*viewDataBinding.outerLayout.setOnClickListener {
                mClickListener.onClick(viewDataBinding.outerLayout, position)
            }*/
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