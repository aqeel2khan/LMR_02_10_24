package com.lmr.appmodule.vendor.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lmr.R
import com.lmr.appmodule.BaseFragment
import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import com.lmr.databinding.FragmentPhotographersBinding
import com.lmr.appmodule.vendor.viewmodel.PhotographersFragmentViewModel
import com.lmr.appmodule.vendor.viewmodel.VendorFragmentViewModel
import com.lmr.databinding.FragmentVendorBinding
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PhotographersFragment : BaseFragment<FragmentPhotographersBinding>()  {

    private val mPhotographersFragmentViewModel: PhotographersFragmentViewModel by viewModels()
    override fun getViewModel(): BaseViewModel {
        return mPhotographersFragmentViewModel
    }

    override fun initUi() {
        mPhotographersFragmentViewModel.init()
    }

    override fun getViewBinding() = FragmentPhotographersBinding.inflate(layoutInflater).apply {
        lifecycleOwner = this@PhotographersFragment
        photographersFragmentViewModel = this@PhotographersFragment.mPhotographersFragmentViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModelLiveData()
    }
    private fun observeViewModelLiveData() {
        mPhotographersFragmentViewModel.mBackButtonClick.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandledOrReturnNull()?.let {
                findNavController().navigate(R.id.action_PhotographersFragment_to_VendorFragment)
            }
        }
    }
}