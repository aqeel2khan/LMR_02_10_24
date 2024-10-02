package com.lmr.appmodule.vendor.viewmodel

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.lmr.R
import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import com.lmr.appmodule.vendor.model.VendorDataItem
import com.lmr.appmodule.vendor.model.VendorEventDataItem
import com.lmr.appmodule.vendor.ui.adapter.VenueLocationAdapter
import com.lmr.appmodule.vendor.utils.AdaptiveSpacingItemDecoration
import com.lmr.appmodule.vendor.utils.CenterZoomLayoutManager
import com.lmr.appmodule.vendor.utils.Event
import com.lmr.appmodule.vendor.utils.OnClickListener

class VenueLocationFragmentViewModel: BaseViewModel() {

    val mBackButtonClick = MutableLiveData<Event<String>>()
    val mVenueLocationAdapter = ObservableField<VenueLocationAdapter>()

    private val mOnClickListener = object : OnClickListener {
        override fun <T> onClick(view: View, item: T) {
            if (item is VendorDataItem) {
                if (view.id == R.id.view_category) {
                }
            }
        }
    }

    fun init() {
        val venueLocationAdapter = VenueLocationAdapter(mVendorEventsList = getVenueLocation(), mClickListener = mOnClickListener)
        mVenueLocationAdapter.set(venueLocationAdapter)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.view_back -> {
                mBackButtonClick.value = Event("Back_Button")
            }
        }
    }

    private fun getVenueLocation(): ArrayList<VendorEventDataItem> {
        val venueLocationList = ArrayList<VendorEventDataItem>()

        val vendorEventDataItem1 = VendorEventDataItem(
            "Venue",
            "Events",
            "Events",
            "Events",
            10,
            10,
        )
        val vendorEventDataItem2 = VendorEventDataItem(
            "Venue",
            "Events",
            "Events",
            "Events",
            10,
            10,
        )
        venueLocationList.add(vendorEventDataItem1)
        venueLocationList.add(vendorEventDataItem2)
        venueLocationList.add(vendorEventDataItem2)
        venueLocationList.add(vendorEventDataItem2)
        venueLocationList.add(vendorEventDataItem2)
        venueLocationList.add(vendorEventDataItem2)
        venueLocationList.add(vendorEventDataItem2)
        venueLocationList.add(vendorEventDataItem2)
        venueLocationList.add(vendorEventDataItem2)
        venueLocationList.add(vendorEventDataItem2)
        venueLocationList.add(vendorEventDataItem2)
        venueLocationList.add(vendorEventDataItem2)
        return venueLocationList
    }

    companion object {
        @BindingAdapter("setVenueLocationAdapter")
        @JvmStatic
        fun setVenueLocationAdapter(recyclerView: RecyclerView, venueLocationAdapter: VenueLocationAdapter) {
            recyclerView.layoutManager = CenterZoomLayoutManager(recyclerView.context)
            recyclerView.adapter = venueLocationAdapter
            recyclerView.addItemDecoration(AdaptiveSpacingItemDecoration(recyclerView.context.resources.getDimensionPixelSize(R.dimen.spacing), edgeEnabled = true))
            // Create PagerSnapHelper and attach it to RecyclerView
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(recyclerView)
        }
    }
}