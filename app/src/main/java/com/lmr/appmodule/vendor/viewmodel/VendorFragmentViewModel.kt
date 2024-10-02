package com.lmr.appmodule.vendor.viewmodel

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lmr.R
import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import com.lmr.appmodule.vendor.model.VendorDataItem
import com.lmr.appmodule.vendor.model.VendorEventDataItem
import com.lmr.appmodule.vendor.ui.adapter.HomeVendorAdapter
import com.lmr.appmodule.vendor.utils.Event
import com.lmr.appmodule.vendor.utils.OnClickListener

class VendorFragmentViewModel: BaseViewModel() {

    val mAdapter = ObservableField<HomeVendorAdapter>()
    val mVendorCategoryClick = MutableLiveData<Event<String>>()
    val mVenuesViewMoreClick = MutableLiveData<Event<String>>()
    val mVenueCardViewClick = MutableLiveData<Event<String>>()
    val mPhotographersViewMoreClick = MutableLiveData<Event<String>>()

    private val mOnClickListener = object : OnClickListener {
        override fun <T> onClick(view: View, item: T) {
            if (item is VendorDataItem) {
                if (view.id == R.id.view_more) {
                    when(item.vendorType) {
                        "Vendor" -> {

                        }
                        "Venue" -> {
                            mVenuesViewMoreClick.value = Event(item.vendorName.toString())
                        }
                        "Filming" -> {
                            mPhotographersViewMoreClick.value = Event(item.vendorName.toString())
                        }
                    }
                }
            }
            if (item is VendorEventDataItem) {
                if (view.id == R.id.item_category) {
                    mVendorCategoryClick.value = Event(item.vendorEventName.toString())
                }
                if (view.id == R.id.card_view_vendor_venue_item) {
                    mVenueCardViewClick.value = Event(item.vendorEventName.toString())
                }
            }
        }
    }

    fun init() {
        //val mContext = getApplication<App>().applicationContext
        val homeVendorAdapter = HomeVendorAdapter(mVendorList = getVendors(), mClickListener = mOnClickListener)
        mAdapter.set(homeVendorAdapter)
    }

    private fun getVendors(): ArrayList<VendorDataItem> {
        val vendorsList = ArrayList<VendorDataItem>()
        val vendorEventsList = ArrayList<VendorEventDataItem>()

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
        vendorEventsList.add(vendorEventDataItem1)
        vendorEventsList.add(vendorEventDataItem2)
        vendorEventsList.add(vendorEventDataItem2)
        vendorEventsList.add(vendorEventDataItem2)
        vendorEventsList.add(vendorEventDataItem2)
        vendorEventsList.add(vendorEventDataItem2)

        val vendorDataItem1 = VendorDataItem(
            vendorLabel = "Events",
            vendorName = "Vendor Categories",
            vendorType = "Vendor",
            vendorCount = 10,
            isBackGround = true,
            backGroundColor = "#FAFAFA",
            backGroundImage = "",
            vendorEventsList = vendorEventsList
        )

        val vendorDataItem2 = VendorDataItem(
            vendorLabel = "Events",
            vendorName = "Venues",
            vendorType = "Venue",
            vendorCount = 60,
            isBackGround = true,
            backGroundColor = "#D9D9D9",
            backGroundImage = "",
            vendorEventsList = vendorEventsList
        )

        val vendorDataItem3 = VendorDataItem(
            vendorLabel = "Events",
            vendorName = "Planning Tools",
            vendorType = "Tools",
            vendorCount = 10,
            isBackGround = false,
            backGroundColor = "#FFFFFF",
            backGroundImage = "",
            vendorEventsList = vendorEventsList
        )

        val vendorDataItem4 = VendorDataItem(
            vendorLabel = "Events",
            vendorName = "Photographers",
            vendorType = "Filming",
            vendorCount = 10,
            isBackGround = false,
            backGroundColor = "#FFFFFF",
            backGroundImage = "",
            vendorEventsList = vendorEventsList
        )
        vendorsList.add(vendorDataItem1)
        vendorsList.add(vendorDataItem2)
        vendorsList.add(vendorDataItem3)
        vendorsList.add(vendorDataItem4)
        return vendorsList
    }

    companion object {
        @BindingAdapter("setHomeVendorAdapter")
        @JvmStatic
        fun setHomeVendorAdapter(recyclerView: RecyclerView, homeVendorAdapter: HomeVendorAdapter) {
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
            recyclerView.adapter = homeVendorAdapter
        }
    }
}