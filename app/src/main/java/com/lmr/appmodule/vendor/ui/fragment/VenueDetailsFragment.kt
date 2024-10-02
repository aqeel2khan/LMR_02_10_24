package com.lmr.appmodule.vendor.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lmr.R
import com.lmr.appmodule.BaseFragment
import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import com.lmr.appmodule.vendor.viewmodel.VenueDetailsFragmentViewModel
import com.lmr.databinding.FragmentVenueDetailsBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class VenueDetailsFragment : BaseFragment<FragmentVenueDetailsBinding>()  {

    private val mVenueDetailsFragmentViewModel: VenueDetailsFragmentViewModel by viewModels()
    override fun getViewModel(): BaseViewModel {
        return mVenueDetailsFragmentViewModel
    }
    override fun initUi() {
        activity?.let { mVenueDetailsFragmentViewModel.init(it.applicationContext) }
    }
    override fun getViewBinding() = FragmentVenueDetailsBinding.inflate(layoutInflater).apply {
        lifecycleOwner = this@VenueDetailsFragment
        venueDetailsFragmentViewModel = this@VenueDetailsFragment.mVenueDetailsFragmentViewModel
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModelLiveData()
    }
    private fun observeViewModelLiveData() {
        mVenueDetailsFragmentViewModel.mBackButtonClick.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandledOrReturnNull()?.let {
                findNavController().navigate(R.id.action_VenueDetailsFragment_to_VendorFragment)
            }
        }

        mVenueDetailsFragmentViewModel.mVenueLocationNavClick.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandledOrReturnNull()?.let {
                findNavController().navigate(R.id.action_VenueDetailsFragment_to_VenueLocationFragment)
            }
        }
    }
}