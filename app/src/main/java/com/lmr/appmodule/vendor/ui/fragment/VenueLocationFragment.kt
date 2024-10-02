package com.lmr.appmodule.vendor.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lmr.R
import com.lmr.appmodule.BaseFragment
import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import com.lmr.appmodule.vendor.viewmodel.VenueLocationFragmentViewModel
import com.lmr.databinding.FragmentVenueLocationBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class VenueLocationFragment : BaseFragment<FragmentVenueLocationBinding>()  {

    private val mVenueLocationFragmentViewModel: VenueLocationFragmentViewModel by viewModels()
    override fun getViewModel(): BaseViewModel {
        return mVenueLocationFragmentViewModel
    }

    override fun initUi() {
        mVenueLocationFragmentViewModel.init()
    }

    override fun getViewBinding() = FragmentVenueLocationBinding.inflate(layoutInflater).apply {
        lifecycleOwner = this@VenueLocationFragment
        venueLocationFragmentViewModel = this@VenueLocationFragment.mVenueLocationFragmentViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModelLiveData()
    }
    private fun observeViewModelLiveData() {
        mVenueLocationFragmentViewModel.mBackButtonClick.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandledOrReturnNull()?.let {
                findNavController().navigate(R.id.action_VenueLocationFragment_to_VenueDetailsFragment)
            }
        }
    }
}