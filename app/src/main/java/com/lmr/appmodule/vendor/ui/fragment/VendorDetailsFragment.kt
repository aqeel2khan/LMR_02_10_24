package com.lmr.appmodule.vendor.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lmr.R
import com.lmr.appmodule.BaseFragment
import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import com.lmr.appmodule.vendor.viewmodel.VendorDetailsFragmentViewModel
import com.lmr.databinding.FragmentVendorDetailsBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class VendorDetailsFragment : BaseFragment<FragmentVendorDetailsBinding>()  {
    private val mVendorDetailsFragmentViewModel: VendorDetailsFragmentViewModel by viewModels()
    override fun getViewModel(): BaseViewModel {
        return mVendorDetailsFragmentViewModel
    }
    override fun initUi() {
        mVendorDetailsFragmentViewModel.init()
    }
    override fun getViewBinding() = FragmentVendorDetailsBinding.inflate(layoutInflater).apply {
        lifecycleOwner = this@VendorDetailsFragment
        vendorDetailsFragmentViewModel = this@VendorDetailsFragment.mVendorDetailsFragmentViewModel
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModelLiveData()
    }
    private fun observeViewModelLiveData() {
        mVendorDetailsFragmentViewModel.mBackButtonClick.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandledOrReturnNull()?.let {
                findNavController().navigate(R.id.action_VendorDetailsFragment_to_VendorFragment)
            }
        }
    }
}