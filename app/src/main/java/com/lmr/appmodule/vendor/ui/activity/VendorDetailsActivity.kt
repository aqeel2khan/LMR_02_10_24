package com.lmr.appmodule.vendor.ui.activity

import androidx.activity.viewModels
import com.lmr.appmodule.BaseActivity
import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import com.lmr.appmodule.vendor.viewmodel.VendorDetailsActivityViewModel
import com.lmr.databinding.ActivityVendorDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VendorDetailsActivity :  BaseActivity<ActivityVendorDetailsBinding>() {

    private val mVendorDetailsActivityViewModel: VendorDetailsActivityViewModel by viewModels()
    override fun getViewModel(): BaseViewModel {
        return  mVendorDetailsActivityViewModel
    }

    override fun initUi() {
        /*val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        //<-- Header Text View -->
        val detailsTextView = findViewById<TextView>(R.id.detailsTextView)
        detailsTextView.text = "Location Details"
        binding.addLocationLater.setOnClickListener {
            startActivity(Intent(this, EventOrganizerDetailsActivity::class.java))
        }    */

        mVendorDetailsActivityViewModel.init()
        observeViewModelLiveData()
    }
    override fun getViewBinding()= ActivityVendorDetailsBinding.inflate(layoutInflater).apply {
        lifecycleOwner = this@VendorDetailsActivity
        vendorDetailsActivityViewModel = this@VendorDetailsActivity.mVendorDetailsActivityViewModel
    }

    private fun observeViewModelLiveData() {
        mVendorDetailsActivityViewModel.mBackButtonClick.observe(this) { event ->
            event.getContentIfNotHandledOrReturnNull()?.let {
                finish()
            }
        }
    }
}