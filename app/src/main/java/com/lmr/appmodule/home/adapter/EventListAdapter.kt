package com.dev.batchfinal.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lmr.app_utils.MyUtils
import com.lmr.appmodule.home.model.EventDashboardCategory
import com.lmr.appmodule.home.model.EventData
import com.lmr.databinding.ItemsEventsListBinding
import java.text.SimpleDateFormat
import java.util.Locale


class EventListAdapter(
    private var mContext:Context,
    private var eventDashboardCategory: List<EventData>,
    ) : RecyclerView.Adapter<EventListAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: ItemsEventsListBinding = ItemsEventsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding,mContext)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(eventDashboardCategory,position)
    }

    override fun getItemCount(): Int {
        return eventDashboardCategory.size
    }




    inner class ViewHolder(val binding: ItemsEventsListBinding, val mContext: Context) : RecyclerView.ViewHolder(binding.root) {

        fun bind(eventDashboard_Category: List<EventData>, position: Int) {
           // Toast.makeText(mContext, spotlightList[position].toString(), Toast.LENGTH_SHORT).show()
            try {
                binding.txtEventName.text= eventDashboard_Category[position].eventName
                MyUtils.loadImageWithProgress(
                    binding.ivToday1,
                    eventDashboard_Category[position].images.toString(),mContext
                )

                MyUtils.loadImageWithProgress(
                    binding.userimg,
                    eventDashboard_Category[position].profileImage.toString(),mContext
                )

                val dateString = eventDashboard_Category[position].eventStartDate
                val inputFormat = SimpleDateFormat("MM/dd/yyyy hh:mm:ss a", Locale.ENGLISH)
                val date = inputFormat.parse(dateString)

                // Get Date
                val dateFormat = SimpleDateFormat("d", Locale.ENGLISH)
                val dateOutput = dateFormat.format(date)

                // Get Day
                val dayFormat = SimpleDateFormat("EEE", Locale.ENGLISH)
                val dayOutput = dayFormat.format(date)

                // Get Month
                val monthFormat = SimpleDateFormat("MMM", Locale.ENGLISH)
                val monthOutput = monthFormat.format(date)

                println("Date: $dateOutput")
                println("Day: $dayOutput")
                println("Month: $monthOutput")
                binding.month.text= monthOutput
                binding.date.text= dateOutput
                binding.day.text= dayOutput

            }catch (e:Exception){}
           /* binding.month.text= eventDashboard_Category[position].listdata!![position].startTime
            binding.date.text= eventDashboard_Category[position].listdata!![position].startTime
            binding.day.text= eventDashboard_Category[position].listdata!![position].startTime
            binding.txtEventName.text= eventDashboard_Category[position].listdata!![position].eventName
            binding.txtEventStartEnd.text= eventDashboard_Category[position].listdata!![position].eventStartDate*/





        }


    }




}
