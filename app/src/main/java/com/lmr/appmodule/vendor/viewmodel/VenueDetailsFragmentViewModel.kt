package com.lmr.appmodule.vendor.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.DynamicDrawableSpan
import android.text.style.ImageSpan
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.lmr.R
import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import com.lmr.appmodule.vendor.model.SubFoodMenu
import com.lmr.appmodule.vendor.model.VendorDataItem
import com.lmr.appmodule.vendor.model.VendorPolicyDataItem
import com.lmr.appmodule.vendor.model.VenueAvailableAreaDataItem
import com.lmr.appmodule.vendor.model.VenueMoreDetailsDataItem
import com.lmr.appmodule.vendor.ui.adapter.VenueAvailableAreaAdapter
import com.lmr.appmodule.vendor.ui.adapter.VenueCostDetailsAdapter
import com.lmr.appmodule.vendor.ui.adapter.VenueMoreDetailsAdapter
import com.lmr.appmodule.vendor.utils.Event
import com.lmr.appmodule.vendor.utils.OnClickListener

class VenueDetailsFragmentViewModel: BaseViewModel() {

    val mVenueAvailableAreaAdapter = ObservableField<VenueAvailableAreaAdapter>()
    val mVenueCostDetailsAdapter = ObservableField<VenueCostDetailsAdapter>()
    val mVenueDetailsAdapter = ObservableField<VenueMoreDetailsAdapter>()
    val mVenueMoreDetailsAdapter = ObservableField<VenueMoreDetailsAdapter>()
    val mBackButtonClick = MutableLiveData<Event<String>>()
    val mVenueLocationNavClick = MutableLiveData<Event<String>>()
    val isReadMore = ObservableField(false)

    @SuppressLint("StaticFieldLeak")
    var mContext: Context? =null

    private val mOnClickListener = object : OnClickListener {
        override fun <T> onClick(view: View, item: T) {
            if (item is VendorDataItem) {
                if (view.id == R.id.iv_view_more) {
                }
            }
        }
    }

    fun init(context: Context) {
        mContext = context
        val venueCostDetailsAdapter = VenueCostDetailsAdapter(mVendorPoliciesList = getVenueCostDetails())
        mVenueCostDetailsAdapter.set(venueCostDetailsAdapter)

        val venueAvailableAreaAdapter = VenueAvailableAreaAdapter(mVenueAvailableAreaList = getVenueAvailableAreas())
        mVenueAvailableAreaAdapter.set(venueAvailableAreaAdapter)

        val venueDetailsAdapter = VenueMoreDetailsAdapter(mVenueMoreDetailsList = getVenueDetailsList())
        mVenueDetailsAdapter.set(venueDetailsAdapter)

        val venueMoreDetailsAdapter = VenueMoreDetailsAdapter(mVenueMoreDetailsList = getVenueMoreDetailsList())
        mVenueMoreDetailsAdapter.set(venueMoreDetailsAdapter)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.view_back -> {
                mBackButtonClick.value = Event("Back_Button")
            }
            R.id.iv_venue_nav -> {
                mVenueLocationNavClick.value = Event("Venue_Location")
            }
            R.id.tv_read_more -> {
                if (isReadMore.get() == false) {
                    isReadMore.set(true)
                } else {
                    isReadMore.set(false)
                }
            }
        }
    }

    private fun getVenueAvailableAreas(): ArrayList<VenueAvailableAreaDataItem> {
        val areaList = ArrayList<VenueAvailableAreaDataItem>()
        val venueAvailableAreaDataItem = VenueAvailableAreaDataItem(
            areaImage = "",
            areaName = "",
            floatingSeating = 0,
            seatingSpace = 0,
            areaShape = "",
            floorArea = ""
        )
        areaList.add(venueAvailableAreaDataItem)
        areaList.add(venueAvailableAreaDataItem)
        areaList.add(venueAvailableAreaDataItem)
        areaList.add(venueAvailableAreaDataItem)
        return areaList
    }

    private fun getVenueDetailsList(): ArrayList<VenueMoreDetailsDataItem> {
        val venueMoreDetailsList = ArrayList<VenueMoreDetailsDataItem>()

        val subFoodMenuList1 = ArrayList<SubFoodMenu>()
        subFoodMenuList1.add(SubFoodMenu("Catering Via Menu", true))
        subFoodMenuList1.add(SubFoodMenu("Own Food Allowed", false))
        subFoodMenuList1.add(SubFoodMenu("Own Beverages Allowed", true))
        subFoodMenuList1.add(SubFoodMenu("Meeting Catering", true))
        subFoodMenuList1.add(SubFoodMenu("Dinnerware", true))

        val subFoodMenuList2 = ArrayList<SubFoodMenu>()
        subFoodMenuList2.addAll(subFoodMenuList1)
        subFoodMenuList2.add(SubFoodMenu("Own Food Allowed", false))
        subFoodMenuList2.add(SubFoodMenu("Own Beverages Allowed", true))
        subFoodMenuList2.add(SubFoodMenu("Meeting Catering", true))
        subFoodMenuList2.add(SubFoodMenu("Dinnerware", true))

        val vendorPolicyDataItem1 = VenueMoreDetailsDataItem(
            foodMenu = "Food & Drinks",
            listOfSubMenu = subFoodMenuList1,
            subMenu = getSpannableStringList(subFoodMenuList1)
        )

        val vendorPolicyDataItem2 = VenueMoreDetailsDataItem(
            foodMenu = "Technology",
            listOfSubMenu = subFoodMenuList2,
            subMenu = getSpannableStringList(subFoodMenuList2)
        )

        venueMoreDetailsList.add(vendorPolicyDataItem1)
        venueMoreDetailsList.add(vendorPolicyDataItem2)
        return venueMoreDetailsList
    }

    private fun getVenueMoreDetailsList(): ArrayList<VenueMoreDetailsDataItem> {
        val venueMoreDetailsList = ArrayList<VenueMoreDetailsDataItem>()

        val subFoodMenuList1 = ArrayList<SubFoodMenu>()
        subFoodMenuList1.add(SubFoodMenu("Catering Via Menu", true))
        subFoodMenuList1.add(SubFoodMenu("Own Food Allowed", false))
        subFoodMenuList1.add(SubFoodMenu("Own Beverages Allowed", true))
        subFoodMenuList1.add(SubFoodMenu("Meeting Catering", true))
        subFoodMenuList1.add(SubFoodMenu("Dinnerware", true))

        val subFoodMenuList2 = ArrayList<SubFoodMenu>()
        subFoodMenuList2.addAll(subFoodMenuList1)
        subFoodMenuList2.add(SubFoodMenu("Own Food Allowed", false))
        subFoodMenuList2.add(SubFoodMenu("Own Beverages Allowed", true))
        subFoodMenuList2.add(SubFoodMenu("Meeting Catering", true))
        subFoodMenuList2.add(SubFoodMenu("Dinnerware", true))

        val vendorPolicyDataItem1 = VenueMoreDetailsDataItem(
            foodMenu = "Food & Drinks",
            listOfSubMenu = subFoodMenuList1,
            subMenu = getSpannableStringList(subFoodMenuList1)
        )

        val vendorPolicyDataItem2 = VenueMoreDetailsDataItem(
            foodMenu = "Venue Includes",
            listOfSubMenu = subFoodMenuList2,
            subMenu = getSpannableStringList(subFoodMenuList2)
        )

        val vendorPolicyDataItem3 = VenueMoreDetailsDataItem(
            foodMenu = "Event Type",
            listOfSubMenu = subFoodMenuList1,
            subMenu = getSpannableStringList(subFoodMenuList1)
        )

        val vendorPolicyDataItem4 = VenueMoreDetailsDataItem(
            foodMenu = "Technology",
            listOfSubMenu = subFoodMenuList2,
            subMenu = getSpannableStringList(subFoodMenuList2)
        )

        val vendorPolicyDataItem5 = VenueMoreDetailsDataItem(
            foodMenu = "Technology",
            listOfSubMenu = subFoodMenuList1,
            subMenu = getSpannableStringList(subFoodMenuList1)
        )
        venueMoreDetailsList.add(vendorPolicyDataItem1)
        venueMoreDetailsList.add(vendorPolicyDataItem2)
        venueMoreDetailsList.add(vendorPolicyDataItem3)
        venueMoreDetailsList.add(vendorPolicyDataItem4)
        venueMoreDetailsList.add(vendorPolicyDataItem5)
        venueMoreDetailsList.add(vendorPolicyDataItem1)
        venueMoreDetailsList.add(vendorPolicyDataItem2)
        return venueMoreDetailsList
    }

    private fun getSpannableStringList(list : ArrayList<SubFoodMenu>): SpannableStringBuilder {
        val blueTickDrawable = R.drawable.blue_tick
        val spannableStringBuilder = SpannableStringBuilder("")
        var startIndex = 0
        var endIndex = 0
        for (item in list) {
            spannableStringBuilder.append("# ${item.foodSubMenu}")
            endIndex = startIndex + 1
            spannableStringBuilder.setSpan(
                mContext?.let { ImageSpan(it, blueTickDrawable, DynamicDrawableSpan.ALIGN_CENTER) },
                startIndex,
                endIndex,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannableStringBuilder.append("\n")
            startIndex = spannableStringBuilder.length
            //endIndex = spannableStringBuilder.length + 1
        }
        return spannableStringBuilder
    }

    private fun getVenueCostDetails(): ArrayList<VendorPolicyDataItem> {
        val policiesList = ArrayList<VendorPolicyDataItem>()

        val vendorPolicyDataItem1 = VendorPolicyDataItem(
            policyName = "Price/Person",
            policyPrice = "$ 140",
            policyIcon = "Vendor"
        )

        policiesList.add(vendorPolicyDataItem1)
        policiesList.add(vendorPolicyDataItem1)
        policiesList.add(vendorPolicyDataItem1)
        policiesList.add(vendorPolicyDataItem1)
        policiesList.add(vendorPolicyDataItem1)
        policiesList.add(vendorPolicyDataItem1)
        return policiesList
    }

    companion object {

        @BindingAdapter("setVenueAvailableAreaAdapter")
        @JvmStatic
        fun setVenueAvailableAreaAdapter(recyclerView: RecyclerView, venueAvailableAreaAdapter: VenueAvailableAreaAdapter) {
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = venueAvailableAreaAdapter
        }

        @BindingAdapter("setVenueCostDetailsAdapter")
        @JvmStatic
        fun setVenueCostDetailsAdapter(recyclerView: RecyclerView, venueCostDetailsAdapter: VenueCostDetailsAdapter) {
            recyclerView.layoutManager = GridLayoutManager(recyclerView.context, 2)
            recyclerView.adapter = venueCostDetailsAdapter
        }

        @BindingAdapter("setVenueDetailsAdapter")
        @JvmStatic
        fun setVenueDetailsAdapter(recyclerView: RecyclerView, venueMoreDetailsAdapter: VenueMoreDetailsAdapter) {
            recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            recyclerView.adapter = venueMoreDetailsAdapter
        }

        @BindingAdapter("setVenueMoreDetailsAdapter")
        @JvmStatic
        fun setVenueMoreDetailsAdapter(recyclerView: RecyclerView, venueMoreDetailsAdapter: VenueMoreDetailsAdapter) {
            recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            recyclerView.adapter = venueMoreDetailsAdapter
        }
    }
}