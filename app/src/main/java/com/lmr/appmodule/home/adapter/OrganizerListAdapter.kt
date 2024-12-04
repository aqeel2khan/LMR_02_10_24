package com.dev.batchfinal.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lmr.app_utils.MyUtils
import com.lmr.appmodule.home.model.EventDashboardCategory
import com.lmr.appmodule.home.model.EventData
import com.lmr.databinding.ItemEventOrganizersBinding
import com.lmr.databinding.ItemsEventsListBinding
import java.text.SimpleDateFormat
import java.util.Locale


class OrganizerListAdapter(
    private var mContext:Context,
    private var eventDashboardCategory: List<EventData>,
    ) : RecyclerView.Adapter<OrganizerListAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: ItemEventOrganizersBinding = ItemEventOrganizersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding,mContext)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(eventDashboardCategory,position)
    }

    override fun getItemCount(): Int {
        return eventDashboardCategory.size
    }




    inner class ViewHolder(val binding: ItemEventOrganizersBinding, val mContext: Context) : RecyclerView.ViewHolder(binding.root) {

        fun bind(eventDashboard_Category: List<EventData>, position: Int) {
           // Toast.makeText(mContext, spotlightList[position].toString(), Toast.LENGTH_SHORT).show()
            try {
                binding.txtOrganizerName.text= eventDashboard_Category[position].eventOrganizerName
                MyUtils.loadImageWithProgress(
                    binding.imgOrganizer,
                    eventDashboard_Category[position].profileImage.toString(),mContext
                )



            }catch (e:Exception){}


        }


    }




}
