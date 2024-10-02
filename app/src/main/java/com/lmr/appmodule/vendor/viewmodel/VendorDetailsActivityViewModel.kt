package com.lmr.appmodule.vendor.viewmodel

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lmr.R
import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import com.lmr.appmodule.vendor.model.VendorDataItem
import com.lmr.appmodule.vendor.model.VendorPolicyDataItem
import com.lmr.appmodule.vendor.model.VendorPortfolioDataItem
import com.lmr.appmodule.vendor.model.VendorServiceDataItem
import com.lmr.appmodule.vendor.ui.adapter.VendorPoliciesAdapter
import com.lmr.appmodule.vendor.ui.adapter.VendorPortfolioAdapter
import com.lmr.appmodule.vendor.ui.adapter.VendorServicesAdapter
import com.lmr.appmodule.vendor.utils.Event
import com.lmr.appmodule.vendor.utils.OnClickListener

class VendorDetailsActivityViewModel: BaseViewModel() {

    val mVendorServicesAdapter = ObservableField<VendorServicesAdapter>()
    val mVendorPortfolioAdapter = ObservableField<VendorPortfolioAdapter>()
    val mVendorPoliciesAdapter = ObservableField<VendorPoliciesAdapter>()
    val mBackButtonClick = MutableLiveData<Event<String>>()
    val isReadMore = ObservableField(false)

    private val mOnClickListener = object : OnClickListener {
        override fun <T> onClick(view: View, item: T) {
            if (item is VendorDataItem) {
                if (view.id == R.id.iv_view_more) {
                }
            }
        }
    }

    fun init() {
        val vendorServicesAdapter = VendorServicesAdapter(mVendorServicesList = getVendorServices(), mClickListener = mOnClickListener)
        mVendorServicesAdapter.set(vendorServicesAdapter)

        val vendorPortfolioAdapter = VendorPortfolioAdapter(mVendorPortfolioList = getVendorPortfolio(), mClickListener = mOnClickListener)
        mVendorPortfolioAdapter.set(vendorPortfolioAdapter)

        val vendorPoliciesAdapter = VendorPoliciesAdapter(mVendorPoliciesList = getVendorPolicies())
        mVendorPoliciesAdapter.set(vendorPoliciesAdapter)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.view_back -> {
                mBackButtonClick.value = Event("Back_Button")
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

    private fun getVendorServices(): ArrayList<VendorServiceDataItem> {
        val servicesList = ArrayList<VendorServiceDataItem>()

        val vendorServiceDataItem1 = VendorServiceDataItem(
            serviceName = "Service name in Details",
            serviceDescription = "Service description\\nService description\\nService description",
            serviceIcon = "Vendor",
            servicePrice = "Price Start From : 200 KD"
        )

        servicesList.add(vendorServiceDataItem1)
        servicesList.add(vendorServiceDataItem1)
        servicesList.add(vendorServiceDataItem1)
        servicesList.add(vendorServiceDataItem1)
        return servicesList
    }

    private fun getVendorPortfolio(): ArrayList<VendorPortfolioDataItem> {
        val portfolioList = ArrayList<VendorPortfolioDataItem>()

        val vendorPortfolioDataItem1 = VendorPortfolioDataItem(
            portfolioName = "Service name in Details",
            portfolioAddress = "Service description\\nService description\\nService description",
            portfolioImage = "Vendor"
        )

        portfolioList.add(vendorPortfolioDataItem1)
        portfolioList.add(vendorPortfolioDataItem1)
        portfolioList.add(vendorPortfolioDataItem1)
        portfolioList.add(vendorPortfolioDataItem1)
        return portfolioList
    }

    private fun getVendorPolicies(): ArrayList<VendorPolicyDataItem> {
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
        @BindingAdapter("setVendorServicesAdapter")
        @JvmStatic
        fun setVendorServicesAdapter(recyclerView: RecyclerView, vendorServicesAdapter: VendorServicesAdapter) {
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
            recyclerView.adapter = vendorServicesAdapter
        }

        @BindingAdapter("setVendorPortfolioAdapter")
        @JvmStatic
        fun setVendorPortfolioAdapter(recyclerView: RecyclerView, vendorPortfolioAdapter: VendorPortfolioAdapter) {
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = vendorPortfolioAdapter
        }

        @BindingAdapter("setVendorPoliciesAdapter")
        @JvmStatic
        fun setVendorPoliciesAdapter(recyclerView: RecyclerView, vendorPoliciesAdapter: VendorPoliciesAdapter) {
            recyclerView.layoutManager = GridLayoutManager(recyclerView.context, 2)
            recyclerView.adapter = vendorPoliciesAdapter
        }
    }
}