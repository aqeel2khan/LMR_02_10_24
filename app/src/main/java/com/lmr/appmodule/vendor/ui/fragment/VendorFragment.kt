package com.lmr.appmodule.vendor.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lmr.R
import com.lmr.appmodule.BaseFragment
import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import com.lmr.appmodule.vendor.ui.activity.VendorDetailsActivity
import com.lmr.appmodule.vendor.viewmodel.VendorFragmentViewModel
import com.lmr.databinding.FragmentVendorBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class VendorFragment : BaseFragment<FragmentVendorBinding>() {

    private val mVendorFragmentViewModel: VendorFragmentViewModel by viewModels()
    override fun getViewModel(): BaseViewModel {
        return mVendorFragmentViewModel
    }

    override fun initUi() {
        mVendorFragmentViewModel.init()
    }

    override fun getViewBinding() = FragmentVendorBinding.inflate(layoutInflater).apply {
        lifecycleOwner = this@VendorFragment
        vendorFragmentViewModel = this@VendorFragment.mVendorFragmentViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModelLiveData()
    }

    override fun onResume() {
        super.onResume()
        handleHeader(false)
    }

    private fun observeViewModelLiveData() {
        mVendorFragmentViewModel.mVendorCategoryClick.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandledOrReturnNull()?.let {
                findNavController().navigate(R.id.action_VendorFragment_to_VendorDetailsFragment)
                //openVendorDetailsActivity()
            }
        }

        mVendorFragmentViewModel.mVenuesViewMoreClick.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandledOrReturnNull()?.let {
                findNavController().navigate(R.id.action_VendorFragment_to_VenuesFragment)
            }
        }

        mVendorFragmentViewModel.mVenueCardViewClick.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandledOrReturnNull()?.let {
                findNavController().navigate(R.id.action_VendorFragment_to_VenueDetailsFragment)
            }
        }

        mVendorFragmentViewModel.mPhotographersViewMoreClick.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandledOrReturnNull()?.let {
                findNavController().navigate(R.id.action_VendorFragment_to_PhotographersFragment)
            }
        }
    }

    private fun openVendorDetailsActivity() {
        val intent = Intent(activity, VendorDetailsActivity::class.java)
        startActivity(intent)
    }
}