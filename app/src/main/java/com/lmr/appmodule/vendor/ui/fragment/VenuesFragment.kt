package com.lmr.appmodule.vendor.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lmr.R
import com.lmr.appmodule.BaseFragment
import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import com.lmr.appmodule.vendor.viewmodel.VenuesFragmentViewModel
import com.lmr.databinding.FragmentVenuesBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class VenuesFragment : BaseFragment<FragmentVenuesBinding>()  {

    private val mVenuesFragmentViewModel: VenuesFragmentViewModel by viewModels()
    override fun getViewModel(): BaseViewModel {
        return mVenuesFragmentViewModel
    }

    override fun initUi() {
        mVenuesFragmentViewModel.init()
    }

    override fun getViewBinding() = FragmentVenuesBinding.inflate(layoutInflater).apply {
        lifecycleOwner = this@VenuesFragment
        venuesFragmentViewModel = this@VenuesFragment.mVenuesFragmentViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModelLiveData()
    }
    private fun observeViewModelLiveData() {
        mVenuesFragmentViewModel.mBackButtonClick.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandledOrReturnNull()?.let {
                findNavController().navigate(R.id.action_VenuesFragment_to_VendorFragment)
            }
        }
    }
}