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
import com.lmr.appmodule.vendor.model.VenuesDataItem
import com.lmr.appmodule.vendor.ui.adapter.VenuesAdapter
import com.lmr.appmodule.vendor.utils.Event
import com.lmr.appmodule.vendor.utils.OnClickListener

class PhotographersFragmentViewModel: BaseViewModel() {

    val mBackButtonClick = MutableLiveData<Event<String>>()
    val mAdapter = ObservableField<VenuesAdapter>()

    private val mOnClickListener = object : OnClickListener {
        override fun <T> onClick(view: View, item: T) {
            if (item is VendorDataItem) {
                if (view.id == R.id.view_category) {
                }
            }
        }
    }

    fun init() {
        val venuesAdapter = VenuesAdapter(mVenuesList = getVenues(), mClickListener = mOnClickListener)
        mAdapter.set(venuesAdapter)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.view_back -> {
                mBackButtonClick.value = Event("Back_Button")
            }
        }
    }

    private fun getVenues(): ArrayList<VenuesDataItem> {
        val venuesList = ArrayList<VenuesDataItem>()
        val venuesDataItem1 = VenuesDataItem(
            venueName= "",
            venueAddress= "",
            venueImage= "",
            venueComment= "",
            venueRating= 0,
            venueReviews= 0,
            areaAvailable= 0,
            startingPrice= ""
        )
        venuesList.add(venuesDataItem1)
        venuesList.add(venuesDataItem1)
        venuesList.add(venuesDataItem1)
        venuesList.add(venuesDataItem1)
        return venuesList
    }

    companion object {
        @BindingAdapter("setVenuesAdapter")
        @JvmStatic
        fun setVenuesAdapter(recyclerView: RecyclerView, venuesAdapter: VenuesAdapter) {
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
            recyclerView.adapter = venuesAdapter
        }
    }
}